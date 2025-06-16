package utez.edu.mx.aplicacionprincipios.model.cliente;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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
}

