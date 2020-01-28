package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.TorneosClubesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TorneosClubes} and its DTO {@link TorneosClubesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TorneosClubesMapper extends EntityMapper<TorneosClubesDTO, TorneosClubes> {


    @Mapping(target = "torneos", ignore = true)
    @Mapping(target = "removeTorneo", ignore = true)
    @Mapping(target = "clubs", ignore = true)
    @Mapping(target = "removeClub", ignore = true)
    TorneosClubes toEntity(TorneosClubesDTO torneosClubesDTO);

    default TorneosClubes fromId(Long id) {
        if (id == null) {
            return null;
        }
        TorneosClubes torneosClubes = new TorneosClubes();
        torneosClubes.setId(id);
        return torneosClubes;
    }
}
