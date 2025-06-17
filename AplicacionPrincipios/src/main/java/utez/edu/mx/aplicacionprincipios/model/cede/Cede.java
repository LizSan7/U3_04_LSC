package utez.edu.mx.aplicacionprincipios.model.cede;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String clave;
    private String estado;
    private String municipio;

    @JsonManagedReference(value="cede-almacen")
    @OneToMany(mappedBy = "cede")
    private List<Almacen> almacenes;
}
