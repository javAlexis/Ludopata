package org.latacunga.repository;

import org.latacunga.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findAllByEstado(String estado);

    List<Persona> findAllByNombres(String nombres);

    Persona findByCedula(String cedula);
}
