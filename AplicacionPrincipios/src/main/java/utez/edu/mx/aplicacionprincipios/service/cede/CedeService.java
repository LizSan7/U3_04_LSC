package utez.edu.mx.aplicacionprincipios.service.cede;

import utez.edu.mx.aplicacionprincipios.model.cede.Cede;

import java.util.*;

public interface CedeService {
    List<Cede> listar();
    Cede guardar(Cede cede);
    Cede obtenerPorId(Integer id);
    Cede actualizar(Integer id, Cede cede);
    void eliminar(Integer id);
}