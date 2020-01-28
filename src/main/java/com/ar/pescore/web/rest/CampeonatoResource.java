package com.ar.pescore.web.rest;

import com.ar.pescore.service.CampeonatoService;
import com.ar.pescore.web.rest.errors.BadRequestAlertException;
import com.ar.pescore.service.dto.CampeonatoDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ar.pescore.domain.Campeonato}.
 */
@RestController
@RequestMapping("/api")
public class CampeonatoResource {

    private final Logger log = LoggerFactory.getLogger(CampeonatoResource.class);

    private static final String ENTITY_NAME = "campeonato";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampeonatoService campeonatoService;

    public CampeonatoResource(CampeonatoService campeonatoService) {
        this.campeonatoService = campeonatoService;
    }

    /**
     * {@code POST  /campeonatoes} : Create a new campeonato.
     *
     * @param campeonatoDTO the campeonatoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campeonatoDTO, or with status {@code 400 (Bad Request)} if the campeonato has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/campeonatoes")
    public ResponseEntity<CampeonatoDTO> createCampeonato(@Valid @RequestBody CampeonatoDTO campeonatoDTO) throws URISyntaxException {
        log.debug("REST request to save Campeonato : {}", campeonatoDTO);
        if (campeonatoDTO.getId() != null) {
            throw new BadRequestAlertException("A new campeonato cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampeonatoDTO result = campeonatoService.save(campeonatoDTO);
        return ResponseEntity.created(new URI("/api/campeonatoes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /campeonatoes} : Updates an existing campeonato.
     *
     * @param campeonatoDTO the campeonatoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campeonatoDTO,
     * or with status {@code 400 (Bad Request)} if the campeonatoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campeonatoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/campeonatoes")
    public ResponseEntity<CampeonatoDTO> updateCampeonato(@Valid @RequestBody CampeonatoDTO campeonatoDTO) throws URISyntaxException {
        log.debug("REST request to update Campeonato : {}", campeonatoDTO);
        if (campeonatoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampeonatoDTO result = campeonatoService.save(campeonatoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, campeonatoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /campeonatoes} : get all the campeonatoes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campeonatoes in body.
     */
    @GetMapping("/campeonatoes")
    public List<CampeonatoDTO> getAllCampeonatoes() {
        log.debug("REST request to get all Campeonatoes");
        return campeonatoService.findAll();
    }

    /**
     * {@code GET  /campeonatoes/:id} : get the "id" campeonato.
     *
     * @param id the id of the campeonatoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campeonatoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/campeonatoes/{id}")
    public ResponseEntity<CampeonatoDTO> getCampeonato(@PathVariable Long id) {
        log.debug("REST request to get Campeonato : {}", id);
        Optional<CampeonatoDTO> campeonatoDTO = campeonatoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campeonatoDTO);
    }

    /**
     * {@code DELETE  /campeonatoes/:id} : delete the "id" campeonato.
     *
     * @param id the id of the campeonatoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/campeonatoes/{id}")
    public ResponseEntity<Void> deleteCampeonato(@PathVariable Long id) {
        log.debug("REST request to delete Campeonato : {}", id);
        campeonatoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
