package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.EspecialidadDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Especialidad} and its DTO {@link EspecialidadDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EspecialidadMapper extends EntityMapper<EspecialidadDTO, Especialidad> {


    @Mapping(target = "torneos", ignore = true)
    @Mapping(target = "removeTorneo", ignore = true)
    Especialidad toEntity(EspecialidadDTO especialidadDTO);

    default Especialidad fromId(Long id) {
        if (id == null) {
            return null;
        }
        Especialidad especialidad = new Especialidad();
        especialidad.setId(id);
        return especialidad;
    }
}
