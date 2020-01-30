package com.ar.pescore.service;

import com.ar.pescore.service.dto.SubCategoriaDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ar.pescore.domain.SubCategoria}.
 */
public interface SubCategoriaService {

    /**
     * Save a subCategoria.
     *
     * @param subCategoriaDTO the entity to save.
     * @return the persisted entity.
     */
    SubCategoriaDTO save(SubCategoriaDTO subCategoriaDTO);

    /**
     * Get all the subCategorias.
     *
     * @return the list of entities.
     */
    List<SubCategoriaDTO> findAll();


    /**
     * Get the "id" subCategoria.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubCategoriaDTO> findOne(Long id);

    /**
     * Delete the "id" subCategoria.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
