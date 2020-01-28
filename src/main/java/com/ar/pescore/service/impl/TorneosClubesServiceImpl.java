package com.ar.pescore.service.impl;

import com.ar.pescore.service.TorneosClubesService;
import com.ar.pescore.domain.TorneosClubes;
import com.ar.pescore.repository.TorneosClubesRepository;
import com.ar.pescore.service.dto.TorneosClubesDTO;
import com.ar.pescore.service.mapper.TorneosClubesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link TorneosClubes}.
 */
@Service
@Transactional
public class TorneosClubesServiceImpl implements TorneosClubesService {

    private final Logger log = LoggerFactory.getLogger(TorneosClubesServiceImpl.class);

    private final TorneosClubesRepository torneosClubesRepository;

    private final TorneosClubesMapper torneosClubesMapper;

    public TorneosClubesServiceImpl(TorneosClubesRepository torneosClubesRepository, TorneosClubesMapper torneosClubesMapper) {
        this.torneosClubesRepository = torneosClubesRepository;
        this.torneosClubesMapper = torneosClubesMapper;
    }

    /**
     * Save a torneosClubes.
     *
     * @param torneosClubesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TorneosClubesDTO save(TorneosClubesDTO torneosClubesDTO) {
        log.debug("Request to save TorneosClubes : {}", torneosClubesDTO);
        TorneosClubes torneosClubes = torneosClubesMapper.toEntity(torneosClubesDTO);
        torneosClubes = torneosClubesRepository.save(torneosClubes);
        return torneosClubesMapper.toDto(torneosClubes);
    }

    /**
     * Get all the torneosClubes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TorneosClubesDTO> findAll() {
        log.debug("Request to get all TorneosClubes");
        return torneosClubesRepository.findAll().stream()
            .map(torneosClubesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one torneosClubes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TorneosClubesDTO> findOne(Long id) {
        log.debug("Request to get TorneosClubes : {}", id);
        return torneosClubesRepository.findById(id)
            .map(torneosClubesMapper::toDto);
    }

    /**
     * Delete the torneosClubes by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TorneosClubes : {}", id);
        torneosClubesRepository.deleteById(id);
    }
}
