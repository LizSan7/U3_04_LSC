package utez.edu.mx.aplicacionprincipios.model.almacen;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import utez.edu.mx.aplicacionprincipios.model.cede.Cede;
import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;

import java.time.LocalDate;
import java.util.List;

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
    private char tamano;

    @JsonBackReference(value="cede-almacen")
    @ManyToOne
    @JoinColumn(name = "cede_id")
    private Cede cede;

    @JsonManagedReference(value="almacen-operacion")
    @OneToMany(mappedBy = "almacen", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("almacen")
    private List<Operacion> operaciones;

}