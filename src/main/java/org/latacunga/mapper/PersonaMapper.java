package org.latacunga.mapper;

import org.latacunga.dto.PersonaDto;
import org.latacunga.entity.Persona;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PersonaMapper {
    Persona toEntity(PersonaDto personaDto);
    PersonaDto toDto(Persona persona);

    List<Persona> toEntity(List<PersonaDto> personaDtos);
    List<PersonaDto> toDto(List<Persona> personas);
}
