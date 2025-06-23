package utez.edu.mx.aplicacionprincipios.service.impl.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.config.EmailAlreadyExistsException;
import utez.edu.mx.aplicacionprincipios.dto.cliente.ClienteDTO;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;
import utez.edu.mx.aplicacionprincipios.model.cliente.ClienteRepository;
import utez.edu.mx.aplicacionprincipios.service.cliente.ClienteService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    @Override
    public ClienteDTO guardar(ClienteDTO dto) {
        if (clienteRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya está registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());

        return convertToDTO(clienteRepository.save(cliente));
    }


    @Override
    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public ClienteDTO obtenerPorId(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        return convertToDTO(cliente);
    }

    @Override
    public ClienteDTO actualizar(Integer id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        clienteRepository.findByCorreo(dto.getCorreo()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new EmailAlreadyExistsException("El correo ya está registrado");
            }
        });

        cliente.setNombreCompleto(dto.getNombreCompleto());
        cliente.setTelefono(dto.getTelefono());
        cliente.setCorreo(dto.getCorreo());

        return convertToDTO(clienteRepository.save(cliente));
    }


    @Override
    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNombreCompleto(cliente.getNombreCompleto());
        dto.setTelefono(cliente.getTelefono());
        dto.setCorreo(cliente.getCorreo());
        return dto;
    }

    @Override
    public ClienteDTO obtenerPorCorreo(String correo) {
        return clienteRepository.findByCorreo(correo)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ese correo"));
    }

}