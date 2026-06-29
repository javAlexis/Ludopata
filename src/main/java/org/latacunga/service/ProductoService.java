package org.latacunga.service;

import org.latacunga.dto.ProductoDto;
import org.latacunga.dto.commons.RespuestaWs;
import org.latacunga.entity.Producto;
import org.latacunga.mapper.ProductoMapper;
import org.latacunga.repository.ProductoRepository;
import org.latacunga.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ProductoMapper productoMapper;

    public List<Producto> consultarProductos() {
        try {
            return productoRepository.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public RespuestaWs guardar(ProductoDto productoDto) {
        try{
            Producto producto = productoMapper.toEntity(productoDto);
            producto.setEstado(Constantes.estadoActivo);
            producto.setFechaCreacion(new Date());
            productoRepository.save(producto);
            RespuestaWs respuesta = new RespuestaWs(Boolean.TRUE, "PRODUCTO GUARDADO", "PRODUCTO GUARDADO EXITOSAMENTE");
            return respuesta;
        }catch (Exception e){
            e.printStackTrace();
            return new RespuestaWs(Boolean.FALSE, e.getMessage(), "Error en la insercion");
        }
    }

    public RespuestaWs eliminarProducto(Long id) {
        try{
            Producto producto = productoRepository.findById(id).get();
            producto.setEstado(Constantes.estadoInactivo);
            producto.setFechaEdicion(new Date());
            productoRepository.save(producto);
            return new RespuestaWs(Boolean.TRUE, "PRODUCTO ELIMINADO", "PRODUCTO ELIMINADO EXITOSAMENTE");
        }catch (Exception e){
            e.printStackTrace();
            return new RespuestaWs(Boolean.FALSE, e.getMessage(), "Error en eliminacion");
        }
    }
}
