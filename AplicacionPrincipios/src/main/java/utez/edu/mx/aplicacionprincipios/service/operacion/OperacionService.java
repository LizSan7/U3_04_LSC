package utez.edu.mx.aplicacionprincipios.service.operacion;

import utez.edu.mx.aplicacionprincipios.dto.operacion.OperacionDTO;
import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;

import java.util.List;

public interface OperacionService {
    OperacionDTO guardar(OperacionDTO dto);
    List<OperacionDTO> listar();
    OperacionDTO obtenerPorId(Integer id);
    OperacionDTO actualizar(Integer id, OperacionDTO dto);
    void eliminar(Integer id);
}