package org.latacunga.resource;

import org.latacunga.dto.CuentaDto;
import org.latacunga.entity.Cuenta;
import org.latacunga.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cuenta")
public class CuentaResource {
    @Autowired
    private CuentaService cuentaService;

    @PostMapping(value = "/list")
    public List<Cuenta> consultarCuentas() {
        try {
            return cuentaService.consultarCuentas();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<?> guardar(@RequestBody CuentaDto cuentaDto) {
        try {
            return new ResponseEntity<>(cuentaService.guardar(cuentaDto), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/eliminar")
    public ResponseEntity<?> eliminar(@RequestBody CuentaDto cuentaDto) {
        try {
            return new ResponseEntity<>(cuentaService.eliminar(cuentaDto.getId()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/codigo")
    public ResponseEntity<?> generarCodigo(@RequestBody CuentaDto dto) {
        try {
            String codigo = cuentaService.generarCodigo(dto.getNivel(), dto.getGrupo() != null ? String.valueOf(dto.getGrupo()) : null, dto.getSecuencia());
            return new ResponseEntity<>(codigo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
