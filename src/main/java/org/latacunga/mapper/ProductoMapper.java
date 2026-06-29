package org.latacunga.mapper;

import org.latacunga.dto.ProductoDto;
import org.latacunga.entity.Catalogo;
import org.latacunga.entity.Producto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
    Producto toEntity(ProductoDto productoDto);
    List<ProductoDto> toDto(List<Producto> entity);
    List<Producto> toEntity(List<ProductoDto> dto);
}
