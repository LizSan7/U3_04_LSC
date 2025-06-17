package utez.edu.mx.aplicacionprincipios.service.impl.operacion;

import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;
import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;
import utez.edu.mx.aplicacionprincipios.model.operacion.OperacionRepository;
import utez.edu.mx.aplicacionprincipios.service.almacen.AlmacenService;
import utez.edu.mx.aplicacionprincipios.service.cliente.ClienteService;
import utez.edu.mx.aplicacionprincipios.service.operacion.OperacionService;

import java.time.LocalDate;
import java.util.List;

@Service
public class OperacionServiceImpl implements OperacionService {

    private final OperacionRepository operacionRepository;
    private final ClienteService clienteService;
    private final AlmacenService almacenService;

    public OperacionServiceImpl(OperacionRepository operacionRepository, ClienteService clienteService, AlmacenService almacenService) {
        this.operacionRepository = operacionRepository;
        this.clienteService = clienteService;
        this.almacenService = almacenService;
    }

    @Override
    public List<Operacion> listar() {
        return operacionRepository.findAll();
    }


    @Override
    public Operacion guardar(Operacion operacion) {
        try {
            Cliente cliente = clienteService.obtenerPorId(operacion.getCliente().getId());
            Almacen almacen = almacenService.obtenerPorId(operacion.getAlmacen().getId());

            operacion.setCliente(cliente);
            operacion.setAlmacen(almacen);
            operacion.setFechaOperacion(LocalDate.now());

            String tipo = operacion.getTipoOperacion().toUpperCase();

            double monto = switch (tipo) {
                case "VENTA" -> almacen.getPrecioVenta();
                case "RENTA" -> almacen.getPrecioRenta();
                default -> throw new RuntimeException("Tipo de operación inválido (usa 'VENTA' o 'RENTA')");
            };

            operacion.setMonto(monto);

            return operacionRepository.save(operacion);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al guardar la operación: " + e.getMessage());
        }
    }

    @Override
    public Operacion obtenerPorId(Integer id) {
        return operacionRepository.findById(id).orElse(null);
    }

    @Override
    public Operacion actualizar(Integer id, Operacion operacion) {
        Operacion existente = obtenerPorId(id);

        Cliente cliente = clienteService.obtenerPorId(operacion.getCliente().getId());
        Almacen almacen = almacenService.obtenerPorId(operacion.getAlmacen().getId());

        String tipo = operacion.getTipoOperacion().toUpperCase();
        double monto = switch (tipo) {
            case "VENTA" -> almacen.getPrecioVenta();
            case "RENTA" -> almacen.getPrecioRenta();
            default -> throw new RuntimeException("Tipo de operación inválido (usa 'VENTA' o 'RENTA')");
        };

        existente.setCliente(cliente);
        existente.setAlmacen(almacen);
        existente.setTipoOperacion(tipo);
        existente.setMonto(monto);

        return operacionRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        Operacion operacion = obtenerPorId(id);
        operacionRepository.delete(operacion);
    }

}
