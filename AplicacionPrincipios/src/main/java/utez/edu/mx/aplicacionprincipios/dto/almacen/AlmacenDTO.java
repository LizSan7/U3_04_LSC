package utez.edu.mx.aplicacionprincipios.dto.almacen;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AlmacenDTO {
    private Integer id;
    private String clave;
    private Double precioVenta;
    private Double precioRenta;
    private String tamano;
    private LocalDate fechaRegistro;
    private Integer cedeId;
}
