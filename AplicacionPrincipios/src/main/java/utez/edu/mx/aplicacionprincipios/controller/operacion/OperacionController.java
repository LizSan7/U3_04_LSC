package utez.edu.mx.aplicacionprincipios.controller.operacion;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.dto.operacion.OperacionDTO;
import utez.edu.mx.aplicacionprincipios.service.operacion.OperacionService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/operaciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OperacionController {

    private final OperacionService operacionService;

    @PostMapping("/")
    public ResponseEntity<?> guardar(@RequestBody OperacionDTO dto) {
        try {
            return ResponseEntity.ok(operacionService.guardar(dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<OperacionDTO>> listar() {
        return ResponseEntity.ok(operacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OperacionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(operacionService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody OperacionDTO dto) {
        try {
            return ResponseEntity.ok(operacionService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            operacionService.eliminar(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("mensaje", "Operación eliminada correctamente");
            }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }
}
