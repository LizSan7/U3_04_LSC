package utez.edu.mx.aplicacionprincipios.controller.almacen;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.service.almacen.AlmacenService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor

public class AlmacenController {

    private final AlmacenService almacenService;

    @GetMapping("/")
    public List<Almacen> listar() {
        return almacenService.listar();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Almacen> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(almacenService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<Almacen> guardar(@RequestBody Almacen almacen) {
        return ResponseEntity.ok(almacenService.guardar(almacen));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizar(@PathVariable Integer id, @RequestBody Almacen almacen) {
        return ResponseEntity.ok(almacenService.actualizar(id, almacen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            almacenService.eliminar(id);
            return ResponseEntity.ok()
                    .body(new HashMap<String, String>() {{
                        put("mensaje", "Almacén eliminado correctamente");
                    }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }
}
