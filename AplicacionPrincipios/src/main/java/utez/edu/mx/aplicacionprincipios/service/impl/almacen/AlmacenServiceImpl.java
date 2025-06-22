package utez.edu.mx.aplicacionprincipios.service.impl.almacen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.dto.almacen.AlmacenDTO;
import utez.edu.mx.aplicacionprincipios.dto.cede.CedeDTO;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.model.almacen.AlmacenRepository;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;
import utez.edu.mx.aplicacionprincipios.model.cede.CedeRepository;
import utez.edu.mx.aplicacionprincipios.service.almacen.AlmacenService;
import utez.edu.mx.aplicacionprincipios.service.cede.CedeService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlmacenServiceImpl implements AlmacenService {
    private final AlmacenRepository almacenRepository;
    private final CedeService cedeService;
    private final CedeRepository cedeRepository;

    @Override
    public AlmacenDTO guardar(AlmacenDTO dto) {
        Almacen almacen = new Almacen();
        almacen.setPrecioVenta(dto.getPrecioVenta());
        almacen.setPrecioRenta(dto.getPrecioRenta());

        if (dto.getTamano() != null && !dto.getTamano().isEmpty()) {
            almacen.setTamano(dto.getTamano().charAt(0));
        } else {
            throw new RuntimeException("Tamaño inválido");
        }

        almacen.setFechaRegistro(LocalDate.now());

        Cede cede = cedeRepository.findById(dto.getCedeId())
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));

        almacen.setCede(cede);

        Almacen savedAlmacen = almacenRepository.save(almacen);

        String claveAlmacen = savedAlmacen.getCede().getClave() + "-A" + savedAlmacen.getId();
        savedAlmacen.setClave(claveAlmacen);

        // 3. Guardar nuevamente con la clave generada
        savedAlmacen = almacenRepository.save(savedAlmacen);

        // 4. Convertir a DTO y devolver
        return convertToDTO(savedAlmacen);
    }


    @Override
    public List<AlmacenDTO> listar() {
        return almacenRepository.findAll().stream()
                .map(almacen -> {
                    AlmacenDTO dto = new AlmacenDTO();
                    dto.setId(almacen.getId());
                    dto.setClave(almacen.getClave());
                    dto.setPrecioVenta(almacen.getPrecioVenta());
                    dto.setPrecioRenta(almacen.getPrecioRenta());
                    dto.setTamano(String.valueOf(almacen.getTamano()));
                    dto.setFechaRegistro(almacen.getFechaRegistro());
                    dto.setCedeId(almacen.getCede().getId());
                    return dto;
                })
                .collect(Collectors.toList()).reversed();
    }

    @Override
    public AlmacenDTO obtenerPorId(Integer id) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

        AlmacenDTO dto = new AlmacenDTO();
        dto.setId(almacen.getId());
        dto.setClave(almacen.getClave());
        dto.setPrecioVenta(almacen.getPrecioVenta());
        dto.setPrecioRenta(almacen.getPrecioRenta());
        dto.setTamano(String.valueOf(almacen.getTamano()));
        dto.setFechaRegistro(almacen.getFechaRegistro());
        dto.setCedeId(almacen.getCede().getId());

        return dto;
    }

    @Override
    public AlmacenDTO actualizar(Integer id, AlmacenDTO dto) {
        Almacen almacen = almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));

        almacen.setPrecioVenta(dto.getPrecioVenta());
        almacen.setPrecioRenta(dto.getPrecioRenta());
        almacen.setTamano(dto.getTamano().charAt(0));

        Almacen updated = almacenRepository.save(almacen);

        return convertToDTO(updated);
    }


    @Override
    public void eliminar(Integer id) {
        almacenRepository.deleteById(id);
    }

    private AlmacenDTO convertToDTO(Almacen almacen) {
        AlmacenDTO dto = new AlmacenDTO();
        dto.setId(almacen.getId());
        dto.setClave(almacen.getClave());
        dto.setPrecioVenta(almacen.getPrecioVenta());
        dto.setPrecioRenta(almacen.getPrecioRenta());
        dto.setTamano(String.valueOf(almacen.getTamano()));
        dto.setFechaRegistro(almacen.getFechaRegistro());
        dto.setCedeId(almacen.getCede().getId());
        return dto;
    }

}