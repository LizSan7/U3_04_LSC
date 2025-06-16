package utez.edu.mx.aplicacionprincipios.service.impl.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;
import utez.edu.mx.aplicacionprincipios.model.cliente.ClienteRepository;
import utez.edu.mx.aplicacionprincipios.service.cliente.ClienteService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> listar() { return clienteRepository.findAll(); }
    public Cliente guardar(Cliente cliente) { return clienteRepository.save(cliente); }
    public Cliente obtenerPorId(Integer id) { return clienteRepository.findById(id).orElseThrow(); }
    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente existente = obtenerPorId(id);
        existente.setNombreCompleto(cliente.getNombreCompleto());
        existente.setTelefono(cliente.getTelefono());
        existente.setCorreo(cliente.getCorreo());
        return clienteRepository.save(existente);
    }
    public void eliminar(Integer id) { clienteRepository.deleteById(id); }
}
