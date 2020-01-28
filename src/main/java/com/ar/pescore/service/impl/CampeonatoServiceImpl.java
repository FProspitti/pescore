package com.ar.pescore.service.impl;

import com.ar.pescore.service.CampeonatoService;
import com.ar.pescore.domain.Campeonato;
import com.ar.pescore.repository.CampeonatoRepository;
import com.ar.pescore.service.dto.CampeonatoDTO;
import com.ar.pescore.service.mapper.CampeonatoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Campeonato}.
 */
@Service
@Transactional
public class CampeonatoServiceImpl implements CampeonatoService {

    private final Logger log = LoggerFactory.getLogger(CampeonatoServiceImpl.class);

    private final CampeonatoRepository campeonatoRepository;

    private final CampeonatoMapper campeonatoMapper;

    public CampeonatoServiceImpl(CampeonatoRepository campeonatoRepository, CampeonatoMapper campeonatoMapper) {
        this.campeonatoRepository = campeonatoRepository;
        this.campeonatoMapper = campeonatoMapper;
    }

    /**
     * Save a campeonato.
     *
     * @param campeonatoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public CampeonatoDTO save(CampeonatoDTO campeonatoDTO) {
        log.debug("Request to save Campeonato : {}", campeonatoDTO);
        Campeonato campeonato = campeonatoMapper.toEntity(campeonatoDTO);
        campeonato = campeonatoRepository.save(campeonato);
        return campeonatoMapper.toDto(campeonato);
    }

    /**
     * Get all the campeonatoes.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CampeonatoDTO> findAll() {
        log.debug("Request to get all Campeonatoes");
        return campeonatoRepository.findAll().stream()
            .map(campeonatoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one campeonato by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CampeonatoDTO> findOne(Long id) {
        log.debug("Request to get Campeonato : {}", id);
        return campeonatoRepository.findById(id)
            .map(campeonatoMapper::toDto);
    }

    /**
     * Delete the campeonato by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Campeonato : {}", id);
        campeonatoRepository.deleteById(id);
    }
}
