package com.ar.pescore.service.impl;

import com.ar.pescore.service.ParticipanteService;
import com.ar.pescore.domain.Participante;
import com.ar.pescore.repository.ParticipanteRepository;
import com.ar.pescore.service.dto.ParticipanteDTO;
import com.ar.pescore.service.mapper.ParticipanteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Participante}.
 */
@Service
@Transactional
public class ParticipanteServiceImpl implements ParticipanteService {

    private final Logger log = LoggerFactory.getLogger(ParticipanteServiceImpl.class);

    private final ParticipanteRepository participanteRepository;

    private final ParticipanteMapper participanteMapper;

    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository, ParticipanteMapper participanteMapper) {
        this.participanteRepository = participanteRepository;
        this.participanteMapper = participanteMapper;
    }

    /**
     * Save a participante.
     *
     * @param participanteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ParticipanteDTO save(ParticipanteDTO participanteDTO) {
        log.debug("Request to save Participante : {}", participanteDTO);
        Participante participante = participanteMapper.toEntity(participanteDTO);
        participante = participanteRepository.save(participante);
        return participanteMapper.toDto(participante);
    }

    /**
     * Get all the participantes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ParticipanteDTO> findAll() {
        log.debug("Request to get all Participantes");
        return participanteRepository.findAllWithEagerRelationships().stream()
            .map(participanteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the participantes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ParticipanteDTO> findAllWithEagerRelationships(Pageable pageable) {
        return participanteRepository.findAllWithEagerRelationships(pageable).map(participanteMapper::toDto);
    }
    

    /**
     * Get one participante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ParticipanteDTO> findOne(Long id) {
        log.debug("Request to get Participante : {}", id);
        return participanteRepository.findOneWithEagerRelationships(id)
            .map(participanteMapper::toDto);
    }

    /**
     * Delete the participante by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Participante : {}", id);
        participanteRepository.deleteById(id);
    }
}
