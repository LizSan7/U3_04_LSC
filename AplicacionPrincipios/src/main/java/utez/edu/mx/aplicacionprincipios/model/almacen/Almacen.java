package utez.edu.mx.aplicacionprincipios.model.almacen;

import jakarta.persistence.*;
import lombok.*;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clave;
    private LocalDate fechaRegistro;
    private double precioVenta;
    private double precioRenta;
    private char tamaño; // G, M, P

    @ManyToOne
    @JoinColumn(name = "cede_id")
    private Cede cede;

}