package utez.edu.mx.aplicacionprincipios.service.impl.operacion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.dto.operacion.OperacionDTO;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.model.almacen.AlmacenRepository;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;
import utez.edu.mx.aplicacionprincipios.model.cliente.ClienteRepository;
import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;
import utez.edu.mx.aplicacionprincipios.model.operacion.OperacionRepository;
import utez.edu.mx.aplicacionprincipios.service.operacion.OperacionService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperacionServiceImpl implements OperacionService {

    private final OperacionRepository operacionRepository;
    private final AlmacenRepository almacenRepository;
    private final ClienteRepository clienteRepository;

    @Override
    public OperacionDTO guardar(OperacionDTO dto) {
        Operacion operacion = new Operacion();
        operacion.setTipoOperacion(dto.getTipoOperacion());

        Almacen almacen = almacenRepository.findById(dto.getAlmacenId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        operacion.setAlmacen(almacen);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        operacion.setCliente(cliente);

        if ("venta".equalsIgnoreCase(dto.getTipoOperacion())) {
            operacion.setMonto(almacen.getPrecioVenta());
        } else if ("renta".equalsIgnoreCase(dto.getTipoOperacion())) {
            operacion.setMonto(almacen.getPrecioRenta());
        } else {
            throw new RuntimeException("Tipo de operación inválido");
        }

        operacion.setFechaOperacion(LocalDate.now());

        return convertToDTO(operacionRepository.save(operacion));
    }

    @Override
    public List<OperacionDTO> listar() {
        return operacionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList())
                .reversed();
    }

    @Override
    public OperacionDTO obtenerPorId(Integer id) {
        Operacion operacion = operacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operación no encontrada"));
        return convertToDTO(operacion);
    }

    @Override
    public OperacionDTO actualizar(Integer id, OperacionDTO dto) {
        Operacion operacion = operacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operación no encontrada"));

        operacion.setTipoOperacion(dto.getTipoOperacion());

        Almacen almacen = almacenRepository.findById(dto.getAlmacenId())
                .orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        operacion.setAlmacen(almacen);

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        operacion.setCliente(cliente);

        if ("venta".equalsIgnoreCase(dto.getTipoOperacion())) {
            operacion.setMonto(almacen.getPrecioVenta());
        } else if ("renta".equalsIgnoreCase(dto.getTipoOperacion())) {
            operacion.setMonto(almacen.getPrecioRenta());
        } else {
            throw new RuntimeException("Tipo de operación inválido");
        }

        return convertToDTO(operacionRepository.save(operacion));
    }


    @Override
    public void eliminar(Integer id) {
        operacionRepository.deleteById(id);
    }

    @Override
    public List<OperacionDTO> obtenerPorCliente(Integer clienteId) {
        return operacionRepository.findByClienteId(clienteId).stream()
                .map(operacion -> convertToDTO((Operacion) operacion))
                .collect(Collectors.toList());
    }

    @Override
    public List<OperacionDTO> obtenerPorAlmacen(Integer almacenId) {
        return operacionRepository.findByAlmacenId(almacenId).stream()
                .map(operacion -> convertToDTO((Operacion) operacion))
                .collect(Collectors.toList());
    }

    private OperacionDTO convertToDTO(Operacion operacion) {
        OperacionDTO dto = new OperacionDTO();
        dto.setId(operacion.getId());
        dto.setTipoOperacion(operacion.getTipoOperacion());
        dto.setMonto(operacion.getMonto());
        dto.setFechaOperacion(operacion.getFechaOperacion());
        dto.setAlmacenId(operacion.getAlmacen().getId());
        dto.setClienteId(operacion.getCliente().getId());
        return dto;
    }
}
