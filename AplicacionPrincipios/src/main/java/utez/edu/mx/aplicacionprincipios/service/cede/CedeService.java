package utez.edu.mx.aplicacionprincipios.service.cede;

import utez.edu.mx.aplicacionprincipios.dto.cede.CedeDTO;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;

import java.util.*;

public interface CedeService {
    CedeDTO guardar(CedeDTO dto);
    List<CedeDTO> listar();
    CedeDTO obtenerPorId(Integer id);
    CedeDTO actualizar(Integer id, CedeDTO dto);
    void eliminar(Integer id);
}