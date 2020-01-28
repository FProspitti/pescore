package com.ar.pescore.web.rest;

import com.ar.pescore.service.TorneosClubesService;
import com.ar.pescore.web.rest.errors.BadRequestAlertException;
import com.ar.pescore.service.dto.TorneosClubesDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.ar.pescore.domain.TorneosClubes}.
 */
@RestController
@RequestMapping("/api")
public class TorneosClubesResource {

    private final Logger log = LoggerFactory.getLogger(TorneosClubesResource.class);

    private static final String ENTITY_NAME = "torneosClubes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TorneosClubesService torneosClubesService;

    public TorneosClubesResource(TorneosClubesService torneosClubesService) {
        this.torneosClubesService = torneosClubesService;
    }

    /**
     * {@code POST  /torneos-clubes} : Create a new torneosClubes.
     *
     * @param torneosClubesDTO the torneosClubesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new torneosClubesDTO, or with status {@code 400 (Bad Request)} if the torneosClubes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/torneos-clubes")
    public ResponseEntity<TorneosClubesDTO> createTorneosClubes(@RequestBody TorneosClubesDTO torneosClubesDTO) throws URISyntaxException {
        log.debug("REST request to save TorneosClubes : {}", torneosClubesDTO);
        if (torneosClubesDTO.getId() != null) {
            throw new BadRequestAlertException("A new torneosClubes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TorneosClubesDTO result = torneosClubesService.save(torneosClubesDTO);
        return ResponseEntity.created(new URI("/api/torneos-clubes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /torneos-clubes} : Updates an existing torneosClubes.
     *
     * @param torneosClubesDTO the torneosClubesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated torneosClubesDTO,
     * or with status {@code 400 (Bad Request)} if the torneosClubesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the torneosClubesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/torneos-clubes")
    public ResponseEntity<TorneosClubesDTO> updateTorneosClubes(@RequestBody TorneosClubesDTO torneosClubesDTO) throws URISyntaxException {
        log.debug("REST request to update TorneosClubes : {}", torneosClubesDTO);
        if (torneosClubesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TorneosClubesDTO result = torneosClubesService.save(torneosClubesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, torneosClubesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /torneos-clubes} : get all the torneosClubes.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of torneosClubes in body.
     */
    @GetMapping("/torneos-clubes")
    public List<TorneosClubesDTO> getAllTorneosClubes() {
        log.debug("REST request to get all TorneosClubes");
        return torneosClubesService.findAll();
    }

    /**
     * {@code GET  /torneos-clubes/:id} : get the "id" torneosClubes.
     *
     * @param id the id of the torneosClubesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the torneosClubesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/torneos-clubes/{id}")
    public ResponseEntity<TorneosClubesDTO> getTorneosClubes(@PathVariable Long id) {
        log.debug("REST request to get TorneosClubes : {}", id);
        Optional<TorneosClubesDTO> torneosClubesDTO = torneosClubesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(torneosClubesDTO);
    }

    /**
     * {@code DELETE  /torneos-clubes/:id} : delete the "id" torneosClubes.
     *
     * @param id the id of the torneosClubesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/torneos-clubes/{id}")
    public ResponseEntity<Void> deleteTorneosClubes(@PathVariable Long id) {
        log.debug("REST request to delete TorneosClubes : {}", id);
        torneosClubesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
