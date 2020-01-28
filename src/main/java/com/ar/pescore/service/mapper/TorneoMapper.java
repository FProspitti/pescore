package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.TorneoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Torneo} and its DTO {@link TorneoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CampeonatoMapper.class, EspecialidadMapper.class, ClubMapper.class, TorneosClubesMapper.class})
public interface TorneoMapper extends EntityMapper<TorneoDTO, Torneo> {

    @Mapping(source = "campeonato.id", target = "campeonatoId")
    @Mapping(source = "especialidad.id", target = "especialidadId")
    @Mapping(source = "club.id", target = "clubId")
    @Mapping(source = "torneosClubes.id", target = "torneosClubesId")
    TorneoDTO toDto(Torneo torneo);

    @Mapping(target = "tarjetas", ignore = true)
    @Mapping(target = "removeTarjeta", ignore = true)
    @Mapping(source = "campeonatoId", target = "campeonato")
    @Mapping(source = "especialidadId", target = "especialidad")
    @Mapping(source = "clubId", target = "club")
    @Mapping(source = "torneosClubesId", target = "torneosClubes")
    Torneo toEntity(TorneoDTO torneoDTO);

    default Torneo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Torneo torneo = new Torneo();
        torneo.setId(id);
        return torneo;
    }
}
