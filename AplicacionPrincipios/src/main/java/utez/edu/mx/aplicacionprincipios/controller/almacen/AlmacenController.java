package utez.edu.mx.aplicacionprincipios.controller.almacen;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.service.almacen.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
public class AlmacenController {
    private final AlmacenService almacenService;

    @GetMapping
    public List<Almacen> listar() { return almacenService.listar(); }
    @GetMapping("/{id}")
    public ResponseEntity<Almacen> obtener(@PathVariable Integer id) { return ResponseEntity.ok(almacenService.obtenerPorId(id)); }
    @PostMapping
    public ResponseEntity<Almacen> guardar(@RequestBody Almacen almacen) { return ResponseEntity.ok(almacenService.guardar(almacen)); }
    @PutMapping("/{id}")
    public ResponseEntity<Almacen> actualizar(@PathVariable Integer id, @RequestBody Almacen almacen) { return ResponseEntity.ok(almacenService.actualizar(id, almacen)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { almacenService.eliminar(id); return ResponseEntity.noContent().build(); }
}
