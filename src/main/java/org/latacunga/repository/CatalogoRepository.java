package org.latacunga.repository;

import org.latacunga.entity.Catalogo;
import org.latacunga.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {

}
