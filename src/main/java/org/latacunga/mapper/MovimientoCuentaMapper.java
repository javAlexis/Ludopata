package org.latacunga.mapper;

import org.latacunga.dto.MovimientoCuentaDto;
import org.latacunga.entity.MovimientoCuenta;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovimientoCuentaMapper {
    @Mapping(source = "cuenta.id", target = "idCuenta")
    MovimientoCuentaDto toDto(MovimientoCuenta movimientoCuenta);

    @Mapping(source = "idCuenta", target = "cuenta.id")
    MovimientoCuenta toEntity(MovimientoCuentaDto movimientoCuentaDto);

    List<MovimientoCuentaDto> toDto(List<MovimientoCuenta> entity);
    List<MovimientoCuenta> toEntity(List<MovimientoCuentaDto> dto);
}
