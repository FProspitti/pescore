package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.ParticipanteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Participante} and its DTO {@link ParticipanteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CampeonatoMapper.class, ClubMapper.class, CategoriaMapper.class})
public interface ParticipanteMapper extends EntityMapper<ParticipanteDTO, Participante> {

    @Mapping(source = "club.id", target = "clubId")
    @Mapping(source = "categoria.id", target = "categoriaId")
    ParticipanteDTO toDto(Participante participante);

    @Mapping(target = "tarjetas", ignore = true)
    @Mapping(target = "removeTarjeta", ignore = true)
    @Mapping(target = "removeCampeonato", ignore = true)
    @Mapping(source = "clubId", target = "club")
    @Mapping(source = "categoriaId", target = "categoria")
    Participante toEntity(ParticipanteDTO participanteDTO);

    default Participante fromId(Long id) {
        if (id == null) {
            return null;
        }
        Participante participante = new Participante();
        participante.setId(id);
        return participante;
    }
}
