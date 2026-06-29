package org.latacunga.service;

import org.latacunga.dto.PersonaDto;
import org.latacunga.dto.commons.RespuestaWs;
import org.latacunga.entity.Persona;
import org.latacunga.mapper.PersonaMapper;
import org.latacunga.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private PersonaMapper personaMapper;

    public List<Persona> consultaPersonas() {
        try {
            return personaRepository.findAll();
        }catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public RespuestaWs consultarPersonasPorEstado(String cedula) {
        try {
            Persona persona = personaRepository.findByCedula(cedula);
            return new RespuestaWs(Boolean.TRUE, persona.toString(), "PERSONA CONSULTADAS EXITOSAMENTE");
        }catch (Exception e) {
            e.printStackTrace();
            return new RespuestaWs(Boolean.FALSE,null, "ERROR AL CONSULTAR PERSONAS");
        }
    }

    public RespuestaWs guardar(PersonaDto personaDto) {
        try{
            Persona persona = personaMapper.toEntity(personaDto);
            personaRepository.save(persona);
            return new RespuestaWs(Boolean.TRUE, "PERSONA REGISTRA", "REGISTRO EXITOSO");
        }catch (Exception e) {
            e.printStackTrace();
            return new RespuestaWs(Boolean.FALSE,null, e.getMessage());
        }
    }
}
