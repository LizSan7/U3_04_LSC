package utez.edu.mx.aplicacionprincipios.model.operacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface OperacionRepository extends JpaRepository<Operacion, Integer> {
    Collection<Object> findByClienteId(Integer clienteId);

    Collection<Object> findByAlmacenId(Integer almacenId);

    boolean existsByAlmacenIdAndTipoOperacionIn(Integer almacenId, List<String> tipos);

}
