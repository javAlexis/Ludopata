package org.latacunga.resource;

import org.latacunga.dto.NivelCuentaDto;
import org.latacunga.entity.NivelCuenta;
import org.latacunga.mapper.NivelCuentaMapper;
import org.latacunga.service.NivelCuentaService;
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
@RequestMapping("/nivel")
public class NivelCuentaResource {
    @Autowired
    private NivelCuentaService nivelCuentaService;
    @Autowired
    private NivelCuentaMapper nivelCuentaMapper;

    @PostMapping(value = "/list")
    public List<NivelCuentaDto> consultarNiveles() {
        try {
            List<NivelCuenta> niveles = nivelCuentaService.consultarNiveles();
            return nivelCuentaMapper.toDto(niveles);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<?> guardar(@RequestBody NivelCuentaDto nivelCuentaDto) {
        try {
            NivelCuenta nivel = nivelCuentaMapper.toEntity(nivelCuentaDto);
            NivelCuenta guardado = nivelCuentaService.guardar(nivel);
            return new ResponseEntity<>(nivelCuentaMapper.toDto(guardado), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
