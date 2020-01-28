package com.ar.pescore.service;

import com.ar.pescore.service.dto.EspecialidadDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ar.pescore.domain.Especialidad}.
 */
public interface EspecialidadService {

    /**
     * Save a especialidad.
     *
     * @param especialidadDTO the entity to save.
     * @return the persisted entity.
     */
    EspecialidadDTO save(EspecialidadDTO especialidadDTO);

    /**
     * Get all the especialidads.
     *
     * @return the list of entities.
     */
    List<EspecialidadDTO> findAll();


    /**
     * Get the "id" especialidad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EspecialidadDTO> findOne(Long id);

    /**
     * Delete the "id" especialidad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
