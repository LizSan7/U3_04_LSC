package utez.edu.mx.aplicacionprincipios.controller.almacen;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.dto.almacen.AlmacenDTO;
import utez.edu.mx.aplicacionprincipios.service.almacen.AlmacenService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
public class AlmacenController {

    private final AlmacenService almacenService;

    @GetMapping("/")
    public List<AlmacenDTO> listar() {
        return almacenService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlmacenDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(almacenService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<AlmacenDTO> guardar(@RequestBody AlmacenDTO dto) {
        return ResponseEntity.ok(almacenService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlmacenDTO> actualizar(@PathVariable Integer id, @RequestBody AlmacenDTO dto) {
        return ResponseEntity.ok(almacenService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            almacenService.eliminar(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("mensaje", "Almacén eliminado correctamente");
            }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }
}
