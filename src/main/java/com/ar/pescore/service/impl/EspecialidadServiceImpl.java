package com.ar.pescore.service.impl;

import com.ar.pescore.service.EspecialidadService;
import com.ar.pescore.domain.Especialidad;
import com.ar.pescore.repository.EspecialidadRepository;
import com.ar.pescore.service.dto.EspecialidadDTO;
import com.ar.pescore.service.mapper.EspecialidadMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Especialidad}.
 */
@Service
@Transactional
public class EspecialidadServiceImpl implements EspecialidadService {

    private final Logger log = LoggerFactory.getLogger(EspecialidadServiceImpl.class);

    private final EspecialidadRepository especialidadRepository;

    private final EspecialidadMapper especialidadMapper;

    public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository, EspecialidadMapper especialidadMapper) {
        this.especialidadRepository = especialidadRepository;
        this.especialidadMapper = especialidadMapper;
    }

    /**
     * Save a especialidad.
     *
     * @param especialidadDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public EspecialidadDTO save(EspecialidadDTO especialidadDTO) {
        log.debug("Request to save Especialidad : {}", especialidadDTO);
        Especialidad especialidad = especialidadMapper.toEntity(especialidadDTO);
        especialidad = especialidadRepository.save(especialidad);
        return especialidadMapper.toDto(especialidad);
    }

    /**
     * Get all the especialidads.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<EspecialidadDTO> findAll() {
        log.debug("Request to get all Especialidads");
        return especialidadRepository.findAll().stream()
            .map(especialidadMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one especialidad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EspecialidadDTO> findOne(Long id) {
        log.debug("Request to get Especialidad : {}", id);
        return especialidadRepository.findById(id)
            .map(especialidadMapper::toDto);
    }

    /**
     * Delete the especialidad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Especialidad : {}", id);
        especialidadRepository.deleteById(id);
    }
}
