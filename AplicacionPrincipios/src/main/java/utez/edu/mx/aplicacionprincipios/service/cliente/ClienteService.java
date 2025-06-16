package utez.edu.mx.aplicacionprincipios.service.cliente;

import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listar();
    Cliente guardar(Cliente cliente);
    Cliente obtenerPorId(Integer id);
    Cliente actualizar(Integer id, Cliente cliente);
    void eliminar(Integer id);
}

