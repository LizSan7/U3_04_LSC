package utez.edu.mx.aplicacionprincipios.controller.cede;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;
import utez.edu.mx.aplicacionprincipios.service.cede.CedeService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/cedes")
@RequiredArgsConstructor
public class CedeController {
    private final CedeService cedeService;

    @GetMapping("/")
    public List<Cede> listar() {
        return cedeService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cede> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(cedeService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<Cede> guardar(@RequestBody Cede cede) {
        return ResponseEntity.ok(cedeService.guardar(cede));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cede> actualizar(@PathVariable Integer id, @RequestBody Cede cede) {
        return ResponseEntity.ok(cedeService.actualizar(id, cede));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            cedeService.eliminar(id);
            return ResponseEntity.ok()
                    .body(new HashMap<String, String>() {{
                        put("mensaje", "CEDE eliminada correctamente");
                    }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new HashMap<String, String>() {{
                        put("error", e.getMessage());
                    }});
        }
    }
}

