package com.ar.pescore.service;

import com.ar.pescore.service.dto.TorneosClubesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ar.pescore.domain.TorneosClubes}.
 */
public interface TorneosClubesService {

    /**
     * Save a torneosClubes.
     *
     * @param torneosClubesDTO the entity to save.
     * @return the persisted entity.
     */
    TorneosClubesDTO save(TorneosClubesDTO torneosClubesDTO);

    /**
     * Get all the torneosClubes.
     *
     * @return the list of entities.
     */
    List<TorneosClubesDTO> findAll();


    /**
     * Get the "id" torneosClubes.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TorneosClubesDTO> findOne(Long id);

    /**
     * Delete the "id" torneosClubes.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
