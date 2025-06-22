package utez.edu.mx.aplicacionprincipios.service.almacen;

import utez.edu.mx.aplicacionprincipios.dto.almacen.AlmacenDTO;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;

import java.util.List;

public interface AlmacenService {
    AlmacenDTO guardar(AlmacenDTO dto);
    List<AlmacenDTO> listar();
    AlmacenDTO obtenerPorId(Integer id);
    AlmacenDTO actualizar(Integer id, AlmacenDTO dto);
    void eliminar(Integer id);
}