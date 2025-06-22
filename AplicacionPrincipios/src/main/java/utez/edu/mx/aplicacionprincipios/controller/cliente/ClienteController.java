package utez.edu.mx.aplicacionprincipios.controller.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.dto.cliente.ClienteDTO;
import utez.edu.mx.aplicacionprincipios.service.cliente.ClienteService;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping("/")
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtener(@PathVariable Integer id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @PostMapping("/")
    public ResponseEntity<ClienteDTO> guardar(@RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(clienteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            clienteService.eliminar(id);
            return ResponseEntity.ok(new HashMap<String, String>() {{
                put("mensaje", "Cliente eliminado correctamente");
            }});
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new HashMap<String, String>() {{
                put("error", e.getMessage());
            }});
        }
    }
}
