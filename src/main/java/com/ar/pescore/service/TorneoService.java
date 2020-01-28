package com.ar.pescore.service;

import com.ar.pescore.service.dto.TorneoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ar.pescore.domain.Torneo}.
 */
public interface TorneoService {

    /**
     * Save a torneo.
     *
     * @param torneoDTO the entity to save.
     * @return the persisted entity.
     */
    TorneoDTO save(TorneoDTO torneoDTO);

    /**
     * Get all the torneos.
     *
     * @return the list of entities.
     */
    List<TorneoDTO> findAll();


    /**
     * Get the "id" torneo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TorneoDTO> findOne(Long id);

    /**
     * Delete the "id" torneo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
