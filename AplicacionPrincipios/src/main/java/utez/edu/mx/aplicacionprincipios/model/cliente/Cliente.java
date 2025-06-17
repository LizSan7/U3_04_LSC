package utez.edu.mx.aplicacionprincipios.model.cliente;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import utez.edu.mx.aplicacionprincipios.model.operacion.Operacion;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nombreCompleto;

    @NotBlank
    private String telefono;

    @Email
    @NotBlank
    private String correo;

    @JsonManagedReference(value="cliente-operacion")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("cliente")
    private List<Operacion> operaciones;

}

