package utez.edu.mx.aplicacionprincipios.service.impl.cede;

import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;
import utez.edu.mx.aplicacionprincipios.model.cede.CedeRepository;
import utez.edu.mx.aplicacionprincipios.service.cede.CedeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class CedeServiceImpl implements CedeService {

    private final CedeRepository cedeRepository;

    public CedeServiceImpl(CedeRepository cedeRepository) {
        this.cedeRepository = cedeRepository;
    }

    @Override
    public List<Cede> listar() {
        return cedeRepository.findAll();
    }

    @Override
    public Cede guardar(Cede cede) {
        // Guardar sin clave primero
        Cede savedCede = cedeRepository.save(cede);

        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        int aleatorio = (int) (Math.random() * 9000) + 1000;
        String clave = "C" + savedCede.getId() + "-" + fecha + "-" + aleatorio;
        savedCede.setClave(clave);

        return cedeRepository.save(savedCede);
    }

    @Override
    public Cede obtenerPorId(Integer id) {
        Optional<Cede> cede = cedeRepository.findById(id);
        return cede.orElse(null);
    }

    @Override
    public Cede actualizar(Integer id, Cede cede) {
        Optional<Cede> cedeOptional = cedeRepository.findById(id);
        if (cedeOptional.isPresent()) {
            Cede existente = cedeOptional.get();
            existente.setEstado(cede.getEstado());
            existente.setMunicipio(cede.getMunicipio());
            return cedeRepository.save(existente);
        }
        return null;
    }

    @Override
    public void eliminar(Integer id) {
        cedeRepository.deleteById(id);
    }
}
