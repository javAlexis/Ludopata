package org.latacunga.mapper;

import org.latacunga.dto.CatalogoDto;
import org.latacunga.entity.Catalogo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatalogoMapper {
    Catalogo toEntity(CatalogoDto dto);
    CatalogoDto toDto(Catalogo entity);

    List<CatalogoDto> toDto(List<Catalogo> entity);
    List<Catalogo> toEntity(List<CatalogoDto> dto);
}
