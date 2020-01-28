package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.TarjetaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Tarjeta} and its DTO {@link TarjetaDTO}.
 */
@Mapper(componentModel = "spring", uses = {TorneoMapper.class, ParticipanteMapper.class})
public interface TarjetaMapper extends EntityMapper<TarjetaDTO, Tarjeta> {

    @Mapping(source = "torneo.id", target = "torneoId")
    @Mapping(source = "participante.id", target = "participanteId")
    TarjetaDTO toDto(Tarjeta tarjeta);

    @Mapping(source = "torneoId", target = "torneo")
    @Mapping(source = "participanteId", target = "participante")
    Tarjeta toEntity(TarjetaDTO tarjetaDTO);

    default Tarjeta fromId(Long id) {
        if (id == null) {
            return null;
        }
        Tarjeta tarjeta = new Tarjeta();
        tarjeta.setId(id);
        return tarjeta;
    }
}
