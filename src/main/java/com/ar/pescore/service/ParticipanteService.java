package com.ar.pescore.service;

import com.ar.pescore.service.dto.ParticipanteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.ar.pescore.domain.Participante}.
 */
public interface ParticipanteService {

    /**
     * Save a participante.
     *
     * @param participanteDTO the entity to save.
     * @return the persisted entity.
     */
    ParticipanteDTO save(ParticipanteDTO participanteDTO);

    /**
     * Get all the participantes.
     *
     * @return the list of entities.
     */
    List<ParticipanteDTO> findAll();

    /**
     * Get all the participantes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<ParticipanteDTO> findAllWithEagerRelationships(Pageable pageable);
    
    /**
     * Get the "id" participante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ParticipanteDTO> findOne(Long id);

    /**
     * Delete the "id" participante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
