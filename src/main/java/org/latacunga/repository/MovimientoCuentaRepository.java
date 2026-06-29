package org.latacunga.repository;

import org.latacunga.entity.MovimientoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MovimientoCuentaRepository extends JpaRepository<MovimientoCuenta, Long> {
    List<MovimientoCuenta> findAllByCuentaId(Long idCuenta);
}
