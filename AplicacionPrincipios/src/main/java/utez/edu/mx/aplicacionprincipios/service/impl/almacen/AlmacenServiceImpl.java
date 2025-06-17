package utez.edu.mx.aplicacionprincipios.service.impl.almacen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.model.almacen.AlmacenRepository;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;
import utez.edu.mx.aplicacionprincipios.model.cede.CedeRepository;
import utez.edu.mx.aplicacionprincipios.service.almacen.AlmacenService;
import utez.edu.mx.aplicacionprincipios.service.cede.CedeService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlmacenServiceImpl implements AlmacenService {

    private final AlmacenRepository almacenRepository;

    private final CedeService cedeService;

    @Override
    public Almacen guardar(Almacen almacen) {
        Integer cedeId = (almacen.getCede() != null) ? almacen.getCede().getId() : null;

        if (cedeId == null) {
            throw new RuntimeException("ID de CEDE no proporcionado");
        }

        Cede cede = cedeService.obtenerPorId(cedeId);

        if (cede == null || cede.getClave() == null) {
            throw new RuntimeException("La CEDE es nula o no tiene clave al guardar el almacén");
        }

        almacen.setCede(cede);
        almacen.setFechaRegistro(LocalDate.now());

        Almacen savedAlmacen = almacenRepository.save(almacen);

        String clave = cede.getClave() + "A" + savedAlmacen.getId();
        savedAlmacen.setClave(clave);

        return almacenRepository.save(savedAlmacen);
    }



    @Override
    public List<Almacen> listar() {
        return almacenRepository.findAll();
    }


    @Override
    public Almacen obtenerPorId(Integer id) {
        return almacenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el almacén con ID: " + id));
    }
    @Override
    public Almacen actualizar(Integer id, Almacen almacen) {
        Almacen existente = obtenerPorId(id);
        existente.setPrecioVenta(almacen.getPrecioVenta());
        existente.setPrecioRenta(almacen.getPrecioRenta());
        existente.setTamaño(almacen.getTamaño());
        return almacenRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        almacenRepository.deleteById(id);
    }
}
