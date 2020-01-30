package com.ar.pescore.service.mapper;

import com.ar.pescore.domain.*;
import com.ar.pescore.service.dto.SubCategoriaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SubCategoria} and its DTO {@link SubCategoriaDTO}.
 */
@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface SubCategoriaMapper extends EntityMapper<SubCategoriaDTO, SubCategoria> {

    @Mapping(source = "categoria.id", target = "categoriaId")
    SubCategoriaDTO toDto(SubCategoria subCategoria);

    @Mapping(target = "participantes", ignore = true)
    @Mapping(target = "removeParticipante", ignore = true)
    @Mapping(source = "categoriaId", target = "categoria")
    SubCategoria toEntity(SubCategoriaDTO subCategoriaDTO);

    default SubCategoria fromId(Long id) {
        if (id == null) {
            return null;
        }
        SubCategoria subCategoria = new SubCategoria();
        subCategoria.setId(id);
        return subCategoria;
    }
}
