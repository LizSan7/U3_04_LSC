package utez.edu.mx.aplicacionprincipios.service.almacen;

import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;

import java.util.List;

public interface AlmacenService {
    List<Almacen> listar();
    Almacen guardar(Almacen almacen);
    Almacen obtenerPorId(Integer id);
    Almacen actualizar(Integer id, Almacen almacen);
    void eliminar(Integer id);
}