package utez.edu.mx.aplicacionprincipios.service.operacion;

import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;

import java.util.List;

public interface OperacionService {
    Operacion guardar(Operacion operacion);
    List<Operacion> listar();
    Operacion obtenerPorId(Integer id);
    Operacion actualizar(Integer id, Operacion operacion);
    void eliminar(Integer id);
}