package com.ar.pescore.web.rest;

import com.ar.pescore.PescoreApp;
import com.ar.pescore.domain.TorneosClubes;
import com.ar.pescore.repository.TorneosClubesRepository;
import com.ar.pescore.service.TorneosClubesService;
import com.ar.pescore.service.dto.TorneosClubesDTO;
import com.ar.pescore.service.mapper.TorneosClubesMapper;
import com.ar.pescore.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.ar.pescore.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TorneosClubesResource} REST controller.
 */
@SpringBootTest(classes = PescoreApp.class)
public class TorneosClubesResourceIT {

    private static final Integer DEFAULT_POSICION = 1;
    private static final Integer UPDATED_POSICION = 2;

    private static final Integer DEFAULT_PUNTAJE = 1;
    private static final Integer UPDATED_PUNTAJE = 2;

    private static final Integer DEFAULT_PUNTOS = 1;
    private static final Integer UPDATED_PUNTOS = 2;

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private TorneosClubesRepository torneosClubesRepository;

    @Autowired
    private TorneosClubesMapper torneosClubesMapper;

    @Autowired
    private TorneosClubesService torneosClubesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restTorneosClubesMockMvc;

    private TorneosClubes torneosClubes;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TorneosClubesResource torneosClubesResource = new TorneosClubesResource(torneosClubesService);
        this.restTorneosClubesMockMvc = MockMvcBuilders.standaloneSetup(torneosClubesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TorneosClubes createEntity(EntityManager em) {
        TorneosClubes torneosClubes = new TorneosClubes()
            .posicion(DEFAULT_POSICION)
            .puntaje(DEFAULT_PUNTAJE)
            .puntos(DEFAULT_PUNTOS)
            .fecha(DEFAULT_FECHA);
        return torneosClubes;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TorneosClubes createUpdatedEntity(EntityManager em) {
        TorneosClubes torneosClubes = new TorneosClubes()
            .posicion(UPDATED_POSICION)
            .puntaje(UPDATED_PUNTAJE)
            .puntos(UPDATED_PUNTOS)
            .fecha(UPDATED_FECHA);
        return torneosClubes;
    }

    @BeforeEach
    public void initTest() {
        torneosClubes = createEntity(em);
    }

    @Test
    @Transactional
    public void createTorneosClubes() throws Exception {
        int databaseSizeBeforeCreate = torneosClubesRepository.findAll().size();

        // Create the TorneosClubes
        TorneosClubesDTO torneosClubesDTO = torneosClubesMapper.toDto(torneosClubes);
        restTorneosClubesMockMvc.perform(post("/api/torneos-clubes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneosClubesDTO)))
            .andExpect(status().isCreated());

        // Validate the TorneosClubes in the database
        List<TorneosClubes> torneosClubesList = torneosClubesRepository.findAll();
        assertThat(torneosClubesList).hasSize(databaseSizeBeforeCreate + 1);
        TorneosClubes testTorneosClubes = torneosClubesList.get(torneosClubesList.size() - 1);
        assertThat(testTorneosClubes.getPosicion()).isEqualTo(DEFAULT_POSICION);
        assertThat(testTorneosClubes.getPuntaje()).isEqualTo(DEFAULT_PUNTAJE);
        assertThat(testTorneosClubes.getPuntos()).isEqualTo(DEFAULT_PUNTOS);
        assertThat(testTorneosClubes.getFecha()).isEqualTo(DEFAULT_FECHA);
    }

    @Test
    @Transactional
    public void createTorneosClubesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = torneosClubesRepository.findAll().size();

        // Create the TorneosClubes with an existing ID
        torneosClubes.setId(1L);
        TorneosClubesDTO torneosClubesDTO = torneosClubesMapper.toDto(torneosClubes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTorneosClubesMockMvc.perform(post("/api/torneos-clubes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneosClubesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TorneosClubes in the database
        List<TorneosClubes> torneosClubesList = torneosClubesRepository.findAll();
        assertThat(torneosClubesList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTorneosClubes() throws Exception {
        // Initialize the database
        torneosClubesRepository.saveAndFlush(torneosClubes);

        // Get all the torneosClubesList
        restTorneosClubesMockMvc.perform(get("/api/torneos-clubes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(torneosClubes.getId().intValue())))
            .andExpect(jsonPath("$.[*].posicion").value(hasItem(DEFAULT_POSICION)))
            .andExpect(jsonPath("$.[*].puntaje").value(hasItem(DEFAULT_PUNTAJE)))
            .andExpect(jsonPath("$.[*].puntos").value(hasItem(DEFAULT_PUNTOS)))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())));
    }
    
    @Test
    @Transactional
    public void getTorneosClubes() throws Exception {
        // Initialize the database
        torneosClubesRepository.saveAndFlush(torneosClubes);

        // Get the torneosClubes
        restTorneosClubesMockMvc.perform(get("/api/torneos-clubes/{id}", torneosClubes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(torneosClubes.getId().intValue()))
            .andExpect(jsonPath("$.posicion").value(DEFAULT_POSICION))
            .andExpect(jsonPath("$.puntaje").value(DEFAULT_PUNTAJE))
            .andExpect(jsonPath("$.puntos").value(DEFAULT_PUNTOS))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTorneosClubes() throws Exception {
        // Get the torneosClubes
        restTorneosClubesMockMvc.perform(get("/api/torneos-clubes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTorneosClubes() throws Exception {
        // Initialize the database
        torneosClubesRepository.saveAndFlush(torneosClubes);

        int databaseSizeBeforeUpdate = torneosClubesRepository.findAll().size();

        // Update the torneosClubes
        TorneosClubes updatedTorneosClubes = torneosClubesRepository.findById(torneosClubes.getId()).get();
        // Disconnect from session so that the updates on updatedTorneosClubes are not directly saved in db
        em.detach(updatedTorneosClubes);
        updatedTorneosClubes
            .posicion(UPDATED_POSICION)
            .puntaje(UPDATED_PUNTAJE)
            .puntos(UPDATED_PUNTOS)
            .fecha(UPDATED_FECHA);
        TorneosClubesDTO torneosClubesDTO = torneosClubesMapper.toDto(updatedTorneosClubes);

        restTorneosClubesMockMvc.perform(put("/api/torneos-clubes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneosClubesDTO)))
            .andExpect(status().isOk());

        // Validate the TorneosClubes in the database
        List<TorneosClubes> torneosClubesList = torneosClubesRepository.findAll();
        assertThat(torneosClubesList).hasSize(databaseSizeBeforeUpdate);
        TorneosClubes testTorneosClubes = torneosClubesList.get(torneosClubesList.size() - 1);
        assertThat(testTorneosClubes.getPosicion()).isEqualTo(UPDATED_POSICION);
        assertThat(testTorneosClubes.getPuntaje()).isEqualTo(UPDATED_PUNTAJE);
        assertThat(testTorneosClubes.getPuntos()).isEqualTo(UPDATED_PUNTOS);
        assertThat(testTorneosClubes.getFecha()).isEqualTo(UPDATED_FECHA);
    }

    @Test
    @Transactional
    public void updateNonExistingTorneosClubes() throws Exception {
        int databaseSizeBeforeUpdate = torneosClubesRepository.findAll().size();

        // Create the TorneosClubes
        TorneosClubesDTO torneosClubesDTO = torneosClubesMapper.toDto(torneosClubes);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTorneosClubesMockMvc.perform(put("/api/torneos-clubes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneosClubesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TorneosClubes in the database
        List<TorneosClubes> torneosClubesList = torneosClubesRepository.findAll();
        assertThat(torneosClubesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTorneosClubes() throws Exception {
        // Initialize the database
        torneosClubesRepository.saveAndFlush(torneosClubes);

        int databaseSizeBeforeDelete = torneosClubesRepository.findAll().size();

        // Delete the torneosClubes
        restTorneosClubesMockMvc.perform(delete("/api/torneos-clubes/{id}", torneosClubes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TorneosClubes> torneosClubesList = torneosClubesRepository.findAll();
        assertThat(torneosClubesList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
