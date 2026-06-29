package org.latacunga.service;

import org.latacunga.entity.Catalogo;
import org.latacunga.repository.CatalogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogoService {
    @Autowired
    private CatalogoRepository catalogoRepository;
    public List<Catalogo> consultarCatalogos() {
        try {
            List<Catalogo> lista = catalogoRepository.findAll();
            return lista != null ? lista : new ArrayList<>();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
