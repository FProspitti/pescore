package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.CampeonatoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Campeonato} and its DTO {@link CampeonatoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CampeonatoMapper extends EntityMapper<CampeonatoDTO, Campeonato> {


    @Mapping(target = "torneos", ignore = true)
    @Mapping(target = "removeTorneo", ignore = true)
    @Mapping(target = "participantes", ignore = true)
    @Mapping(target = "removeParticipante", ignore = true)
    Campeonato toEntity(CampeonatoDTO campeonatoDTO);

    default Campeonato fromId(Long id) {
        if (id == null) {
            return null;
        }
        Campeonato campeonato = new Campeonato();
        campeonato.setId(id);
        return campeonato;
    }
}
