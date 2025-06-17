package utez.edu.mx.aplicacionprincipios.controller.operacion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;
import utez.edu.mx.aplicacionprincipios.service.operacion.OperacionService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/operaciones")
@CrossOrigin(origins = "*")
public class OperacionController {

    private final OperacionService operacionService;

    public OperacionController(OperacionService operacionService) {
        this.operacionService = operacionService;
    }

    @PostMapping("/")
    public ResponseEntity<?> guardar(@RequestBody Operacion operacion) {
        try {
            return ResponseEntity.ok(operacionService.guardar(operacion));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Operacion>> listar() {
        return ResponseEntity.ok(operacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Operacion> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(operacionService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Operacion operacion) {
        try {
            operacion.setId(id);
            return ResponseEntity.ok(operacionService.actualizar(id, operacion));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            operacionService.eliminar(id);
            return ResponseEntity.ok()
                    .body(new HashMap<String, String>() {{
                        put("mensaje", "Operación eliminada correctamente");
                    }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }
}