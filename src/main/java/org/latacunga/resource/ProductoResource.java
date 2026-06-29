package org.latacunga.resource;

import org.latacunga.dto.ProductoDto;
import org.latacunga.entity.Producto;
import org.latacunga.service.PersonaService;
import org.latacunga.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoResource {
    @Autowired
    private ProductoService productoService;

    @PostMapping(value = "/list")
    public List<Producto> consultarProductos(){
        try {
            return productoService.consultarProductos();
        }catch (Exception e){
            return  new ArrayList<>();
        }
    }

    @PostMapping(value = "/guardar")
    public ResponseEntity<?> guardarProducto(@RequestBody ProductoDto productoDto){
        try{
            return new  ResponseEntity<>(productoService.guardar(productoDto),  HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/eliminar")
    public ResponseEntity<?> eliminarProducto(@RequestBody ProductoDto productoDto){
        try{
            return new  ResponseEntity<>(productoService.eliminarProducto(productoDto.getId()),  HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
