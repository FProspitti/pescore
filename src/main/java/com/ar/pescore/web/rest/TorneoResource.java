package com.ar.pescore.web.rest;

import com.ar.pescore.service.TorneoService;
import com.ar.pescore.web.rest.errors.BadRequestAlertException;
import com.ar.pescore.service.dto.TorneoDTO;

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
 * REST controller for managing {@link com.ar.pescore.domain.Torneo}.
 */
@RestController
@RequestMapping("/api")
public class TorneoResource {

    private final Logger log = LoggerFactory.getLogger(TorneoResource.class);

    private static final String ENTITY_NAME = "torneo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TorneoService torneoService;

    public TorneoResource(TorneoService torneoService) {
        this.torneoService = torneoService;
    }

    /**
     * {@code POST  /torneos} : Create a new torneo.
     *
     * @param torneoDTO the torneoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new torneoDTO, or with status {@code 400 (Bad Request)} if the torneo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/torneos")
    public ResponseEntity<TorneoDTO> createTorneo(@RequestBody TorneoDTO torneoDTO) throws URISyntaxException {
        log.debug("REST request to save Torneo : {}", torneoDTO);
        if (torneoDTO.getId() != null) {
            throw new BadRequestAlertException("A new torneo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TorneoDTO result = torneoService.save(torneoDTO);
        return ResponseEntity.created(new URI("/api/torneos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /torneos} : Updates an existing torneo.
     *
     * @param torneoDTO the torneoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated torneoDTO,
     * or with status {@code 400 (Bad Request)} if the torneoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the torneoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/torneos")
    public ResponseEntity<TorneoDTO> updateTorneo(@RequestBody TorneoDTO torneoDTO) throws URISyntaxException {
        log.debug("REST request to update Torneo : {}", torneoDTO);
        if (torneoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TorneoDTO result = torneoService.save(torneoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, torneoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /torneos} : get all the torneos.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of torneos in body.
     */
    @GetMapping("/torneos")
    public List<TorneoDTO> getAllTorneos() {
        log.debug("REST request to get all Torneos");
        return torneoService.findAll();
    }

    /**
     * {@code GET  /torneos/:id} : get the "id" torneo.
     *
     * @param id the id of the torneoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the torneoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/torneos/{id}")
    public ResponseEntity<TorneoDTO> getTorneo(@PathVariable Long id) {
        log.debug("REST request to get Torneo : {}", id);
        Optional<TorneoDTO> torneoDTO = torneoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(torneoDTO);
    }

    /**
     * {@code DELETE  /torneos/:id} : delete the "id" torneo.
     *
     * @param id the id of the torneoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/torneos/{id}")
    public ResponseEntity<Void> deleteTorneo(@PathVariable Long id) {
        log.debug("REST request to delete Torneo : {}", id);
        torneoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
