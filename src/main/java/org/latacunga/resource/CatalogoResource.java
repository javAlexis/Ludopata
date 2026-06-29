package org.latacunga.resource;

import org.latacunga.entity.Persona;
import org.latacunga.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(name = "/catalogo")
public class CatalogoResource {
    @Autowired
    private CatalogoService catalogoService;

    @PostMapping(value = "/find")
    public List<?> consultarCatalogo() {
        try{
            return catalogoService.consultarCatalogos();
        }catch(Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
