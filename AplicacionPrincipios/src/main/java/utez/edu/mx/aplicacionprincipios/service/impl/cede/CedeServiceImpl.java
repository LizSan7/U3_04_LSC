package utez.edu.mx.aplicacionprincipios.service.impl.cede;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.dto.cede.CedeDTO;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;
import utez.edu.mx.aplicacionprincipios.model.cede.CedeRepository;
import utez.edu.mx.aplicacionprincipios.service.cede.CedeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CedeServiceImpl implements CedeService {
    private final CedeRepository cedeRepository;

    @Override
    public CedeDTO guardar(CedeDTO dto) {
        Cede cede = new Cede();
        cede.setEstado(dto.getEstado());
        cede.setMunicipio(dto.getMunicipio());

        Cede savedCede = cedeRepository.save(cede);

        String claveGenerada = generarClaveCede(savedCede);
        savedCede.setClave(claveGenerada);

        savedCede = cedeRepository.save(savedCede);

        return convertToDTO(savedCede);
    }

    private String generarClaveCede(Cede cede) {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
        int randomNum = new Random().nextInt(9000) + 1000;
        return "C" + cede.getId() + "-" + fecha + "-" + randomNum;
    }


    @Override
    public List<CedeDTO> listar() {
        return cedeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public CedeDTO obtenerPorId(Integer id) {
        Cede cede = cedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));
        return convertToDTO(cede);
    }

    @Override
    public CedeDTO actualizar(Integer id, CedeDTO dto) {
        Cede cede = cedeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sede no encontrada"));

        cede.setMunicipio(dto.getMunicipio());
        cede.setEstado(dto.getEstado());

        return convertToDTO(cedeRepository.save(cede));
    }

    @Override
    public void eliminar(Integer id) {
        cedeRepository.deleteById(id);
    }

    private CedeDTO convertToDTO(Cede cede) {
        CedeDTO dto = new CedeDTO();
        dto.setId(cede.getId());
        dto.setClave(cede.getClave());
        dto.setMunicipio(cede.getMunicipio());
        dto.setEstado(cede.getEstado());
        return dto;
    }
}
