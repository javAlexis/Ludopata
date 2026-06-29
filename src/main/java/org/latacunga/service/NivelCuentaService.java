package org.latacunga.service;

import org.latacunga.entity.NivelCuenta;
import org.latacunga.repository.NivelCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NivelCuentaService {
    @Autowired
    private NivelCuentaRepository nivelCuentaRepository;

    public List<NivelCuenta> consultarNiveles() {
        try {
            return nivelCuentaRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public NivelCuenta guardar(NivelCuenta nivelCuenta) {
        try {
            return nivelCuentaRepository.save(nivelCuenta);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
