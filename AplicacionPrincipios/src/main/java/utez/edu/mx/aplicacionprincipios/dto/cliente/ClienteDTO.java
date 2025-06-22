package utez.edu.mx.aplicacionprincipios.dto.cliente;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private Integer id;
    private String nombreCompleto;
    private String telefono;
    private String correo;
}