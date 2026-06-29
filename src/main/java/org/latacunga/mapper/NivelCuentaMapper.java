package org.latacunga.mapper;

import org.latacunga.dto.NivelCuentaDto;
import org.latacunga.entity.NivelCuenta;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NivelCuentaMapper {
    NivelCuentaDto toDto(NivelCuenta nivelCuenta);
    NivelCuenta toEntity(NivelCuentaDto nivelCuentaDto);
    List<NivelCuentaDto> toDto(List<NivelCuenta> entity);
    List<NivelCuenta> toEntity(List<NivelCuentaDto> dto);
}
