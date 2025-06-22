package utez.edu.mx.aplicacionprincipios.service.cliente;

import utez.edu.mx.aplicacionprincipios.dto.cliente.ClienteDTO;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;

import java.util.List;

public interface ClienteService {
    ClienteDTO guardar(ClienteDTO dto);
    List<ClienteDTO> listar();
    ClienteDTO obtenerPorId(Integer id);
    ClienteDTO actualizar(Integer id, ClienteDTO dto);
    void eliminar(Integer id);
}

