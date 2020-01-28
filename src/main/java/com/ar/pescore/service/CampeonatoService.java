package com.ar.pescore.service;

import com.ar.pescore.service.dto.CampeonatoDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ar.pescore.domain.Campeonato}.
 */
public interface CampeonatoService {

    /**
     * Save a campeonato.
     *
     * @param campeonatoDTO the entity to save.
     * @return the persisted entity.
     */
    CampeonatoDTO save(CampeonatoDTO campeonatoDTO);

    /**
     * Get all the campeonatoes.
     *
     * @return the list of entities.
     */
    List<CampeonatoDTO> findAll();


    /**
     * Get the "id" campeonato.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampeonatoDTO> findOne(Long id);

    /**
     * Delete the "id" campeonato.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
