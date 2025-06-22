package utez.edu.mx.aplicacionprincipios.controller.cede;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.dto.cede.CedeDTO;
import utez.edu.mx.aplicacionprincipios.service.cede.CedeService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cedes")
@RequiredArgsConstructor
public class CedeController {
    private final CedeService cedeService;

    @GetMapping("/")
    public List<CedeDTO> listar() {
        return cedeService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CedeDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(cedeService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<CedeDTO> guardar(@RequestBody CedeDTO dto) {
        return ResponseEntity.ok(cedeService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CedeDTO> actualizar(@PathVariable Integer id, @RequestBody CedeDTO dto) {
        return ResponseEntity.ok(cedeService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            cedeService.eliminar(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("mensaje", "CEDE eliminada correctamente");
            }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }
}
