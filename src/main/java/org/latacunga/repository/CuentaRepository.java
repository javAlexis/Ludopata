package org.latacunga.repository;

import org.latacunga.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findAllByEstadoOrderByCodigoAsc(String estado);
    Cuenta findByCodigoAndEstado(String codigo, String estado);
}
