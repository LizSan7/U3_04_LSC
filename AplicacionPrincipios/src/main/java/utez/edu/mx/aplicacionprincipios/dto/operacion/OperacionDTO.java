package utez.edu.mx.aplicacionprincipios.dto.operacion;

import lombok.Data;
import java.time.LocalDate;

@Data
public class OperacionDTO {
    private Integer id;
    private String tipoOperacion;
    private Double monto;
    private LocalDate fechaOperacion;
    private Integer almacenId;
    private Integer clienteId;
}