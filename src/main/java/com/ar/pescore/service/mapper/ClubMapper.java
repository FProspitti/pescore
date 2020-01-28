package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.ClubDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Club} and its DTO {@link ClubDTO}.
 */
@Mapper(componentModel = "spring", uses = {TorneosClubesMapper.class})
public interface ClubMapper extends EntityMapper<ClubDTO, Club> {

    @Mapping(source = "torneosClubes.id", target = "torneosClubesId")
    ClubDTO toDto(Club club);

    @Mapping(target = "participantes", ignore = true)
    @Mapping(target = "removeParticipante", ignore = true)
    @Mapping(target = "torneos", ignore = true)
    @Mapping(target = "removeTorneo", ignore = true)
    @Mapping(source = "torneosClubesId", target = "torneosClubes")
    Club toEntity(ClubDTO clubDTO);

    default Club fromId(Long id) {
        if (id == null) {
            return null;
        }
        Club club = new Club();
        club.setId(id);
        return club;
    }
}
