package com.ar.pescore.service.impl;

import com.ar.pescore.service.TorneoService;
import com.ar.pescore.domain.Torneo;
import com.ar.pescore.repository.TorneoRepository;
import com.ar.pescore.service.dto.TorneoDTO;
import com.ar.pescore.service.mapper.TorneoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Torneo}.
 */
@Service
@Transactional
public class TorneoServiceImpl implements TorneoService {

    private final Logger log = LoggerFactory.getLogger(TorneoServiceImpl.class);

    private final TorneoRepository torneoRepository;

    private final TorneoMapper torneoMapper;

    public TorneoServiceImpl(TorneoRepository torneoRepository, TorneoMapper torneoMapper) {
        this.torneoRepository = torneoRepository;
        this.torneoMapper = torneoMapper;
    }

    /**
     * Save a torneo.
     *
     * @param torneoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TorneoDTO save(TorneoDTO torneoDTO) {
        log.debug("Request to save Torneo : {}", torneoDTO);
        Torneo torneo = torneoMapper.toEntity(torneoDTO);
        torneo = torneoRepository.save(torneo);
        return torneoMapper.toDto(torneo);
    }

    /**
     * Get all the torneos.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TorneoDTO> findAll() {
        log.debug("Request to get all Torneos");
        return torneoRepository.findAll().stream()
            .map(torneoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one torneo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TorneoDTO> findOne(Long id) {
        log.debug("Request to get Torneo : {}", id);
        return torneoRepository.findById(id)
            .map(torneoMapper::toDto);
    }

    /**
     * Delete the torneo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Torneo : {}", id);
        torneoRepository.deleteById(id);
    }
}
