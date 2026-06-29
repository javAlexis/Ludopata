package org.latacunga.resource;

import org.latacunga.dto.PersonaDto;
import org.latacunga.dto.commons.SolicitudWs;
import org.latacunga.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona")
public class PersonaResource {
    @Autowired
    private PersonaService personaService;

    @PostMapping("/list")
    public ResponseEntity<?> consultaPersonas(){
        try{
            return new ResponseEntity<>(personaService.consultaPersonas(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cedula")
    public ResponseEntity<?> consultaPersonaCedula(@RequestBody SolicitudWs solicitudWs){
        try{
            return new ResponseEntity<>(personaService.consultarPersonasPorEstado(solicitudWs.getCedula()), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody PersonaDto personaDto){
        try{
            return  new ResponseEntity<>(personaService.guardar(personaDto), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
