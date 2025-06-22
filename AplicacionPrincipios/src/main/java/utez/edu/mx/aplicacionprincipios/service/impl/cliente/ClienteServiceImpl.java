package utez.edu.mx.aplicacionprincipios.service.impl.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
}