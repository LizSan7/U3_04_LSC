package utez.edu.mx.aplicacionprincipios.model.operacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import utez.edu.mx.aplicacionprincipios.model.almacen.Almacen;
import utez.edu.mx.aplicacionprincipios.model.cliente.Cliente;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference(value="cliente-operacion")
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @JsonBackReference(value="almacen-operacion")
    @ManyToOne
    @JoinColumn(name = "almacen_id")
    private Almacen almacen;

    private LocalDate fechaOperacion;

    private String tipoOperacion;

    private Double monto;
}

