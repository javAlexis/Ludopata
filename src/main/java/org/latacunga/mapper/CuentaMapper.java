package org.latacunga.mapper;

import org.latacunga.dto.CuentaDto;
import org.latacunga.entity.Cuenta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    CuentaDto toDto(Cuenta cuenta);
    Cuenta toEntity(CuentaDto cuentaDto);
    List<CuentaDto> toDto(List<Cuenta> entity);
    List<Cuenta> toEntity(List<CuentaDto> dto);
}
