package utez.edu.mx.aplicacionprincipios.controller.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;
import utez.edu.mx.aplicacionprincipios.service.cliente.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() { return clienteService.listar(); }
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable Integer id) { return ResponseEntity.ok(clienteService.obtenerPorId(id)); }
    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) { return ResponseEntity.ok(clienteService.guardar(cliente)); }
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @RequestBody Cliente cliente) { return ResponseEntity.ok(clienteService.actualizar(id, cliente)); }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) { clienteService.eliminar(id); return ResponseEntity.noContent().build(); }
}
