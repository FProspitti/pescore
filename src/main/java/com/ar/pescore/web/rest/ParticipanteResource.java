package com.ar.pescore.web.rest;

import com.ar.pescore.service.ParticipanteService;
import com.ar.pescore.web.rest.errors.BadRequestAlertException;
import com.ar.pescore.service.dto.ParticipanteDTO;

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
 * REST controller for managing {@link com.ar.pescore.domain.Participante}.
 */
@RestController
@RequestMapping("/api")
public class ParticipanteResource {

    private final Logger log = LoggerFactory.getLogger(ParticipanteResource.class);

    private static final String ENTITY_NAME = "participante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ParticipanteService participanteService;

    public ParticipanteResource(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    /**
     * {@code POST  /participantes} : Create a new participante.
     *
     * @param participanteDTO the participanteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new participanteDTO, or with status {@code 400 (Bad Request)} if the participante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/participantes")
    public ResponseEntity<ParticipanteDTO> createParticipante(@Valid @RequestBody ParticipanteDTO participanteDTO) throws URISyntaxException {
        log.debug("REST request to save Participante : {}", participanteDTO);
        if (participanteDTO.getId() != null) {
            throw new BadRequestAlertException("A new participante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ParticipanteDTO result = participanteService.save(participanteDTO);
        return ResponseEntity.created(new URI("/api/participantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /participantes} : Updates an existing participante.
     *
     * @param participanteDTO the participanteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated participanteDTO,
     * or with status {@code 400 (Bad Request)} if the participanteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the participanteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/participantes")
    public ResponseEntity<ParticipanteDTO> updateParticipante(@Valid @RequestBody ParticipanteDTO participanteDTO) throws URISyntaxException {
        log.debug("REST request to update Participante : {}", participanteDTO);
        if (participanteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ParticipanteDTO result = participanteService.save(participanteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, participanteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /participantes} : get all the participantes.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of participantes in body.
     */
    @GetMapping("/participantes")
    public List<ParticipanteDTO> getAllParticipantes(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all Participantes");
        return participanteService.findAll();
    }

    /**
     * {@code GET  /participantes/:id} : get the "id" participante.
     *
     * @param id the id of the participanteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the participanteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/participantes/{id}")
    public ResponseEntity<ParticipanteDTO> getParticipante(@PathVariable Long id) {
        log.debug("REST request to get Participante : {}", id);
        Optional<ParticipanteDTO> participanteDTO = participanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(participanteDTO);
    }

    /**
     * {@code DELETE  /participantes/:id} : delete the "id" participante.
     *
     * @param id the id of the participanteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/participantes/{id}")
    public ResponseEntity<Void> deleteParticipante(@PathVariable Long id) {
        log.debug("REST request to delete Participante : {}", id);
        participanteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
