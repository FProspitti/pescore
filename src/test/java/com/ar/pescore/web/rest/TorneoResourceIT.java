package com.ar.pescore.web.rest;

import com.ar.pescore.PescoreApp;
import com.ar.pescore.domain.Torneo;
import com.ar.pescore.repository.TorneoRepository;
import com.ar.pescore.service.TorneoService;
import com.ar.pescore.service.dto.TorneoDTO;
import com.ar.pescore.service.mapper.TorneoMapper;
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
 * Integration tests for the {@link TorneoResource} REST controller.
 */
@SpringBootTest(classes = PescoreApp.class)
public class TorneoResourceIT {

    private static final LocalDate DEFAULT_FECHA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_OFICIAL = false;
    private static final Boolean UPDATED_OFICIAL = true;

    @Autowired
    private TorneoRepository torneoRepository;

    @Autowired
    private TorneoMapper torneoMapper;

    @Autowired
    private TorneoService torneoService;

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

    private MockMvc restTorneoMockMvc;

    private Torneo torneo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TorneoResource torneoResource = new TorneoResource(torneoService);
        this.restTorneoMockMvc = MockMvcBuilders.standaloneSetup(torneoResource)
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
    public static Torneo createEntity(EntityManager em) {
        Torneo torneo = new Torneo()
            .fecha(DEFAULT_FECHA)
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .nombre(DEFAULT_NOMBRE)
            .oficial(DEFAULT_OFICIAL);
        return torneo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Torneo createUpdatedEntity(EntityManager em) {
        Torneo torneo = new Torneo()
            .fecha(UPDATED_FECHA)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .nombre(UPDATED_NOMBRE)
            .oficial(UPDATED_OFICIAL);
        return torneo;
    }

    @BeforeEach
    public void initTest() {
        torneo = createEntity(em);
    }

    @Test
    @Transactional
    public void createTorneo() throws Exception {
        int databaseSizeBeforeCreate = torneoRepository.findAll().size();

        // Create the Torneo
        TorneoDTO torneoDTO = torneoMapper.toDto(torneo);
        restTorneoMockMvc.perform(post("/api/torneos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneoDTO)))
            .andExpect(status().isCreated());

        // Validate the Torneo in the database
        List<Torneo> torneoList = torneoRepository.findAll();
        assertThat(torneoList).hasSize(databaseSizeBeforeCreate + 1);
        Torneo testTorneo = torneoList.get(torneoList.size() - 1);
        assertThat(testTorneo.getFecha()).isEqualTo(DEFAULT_FECHA);
        assertThat(testTorneo.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testTorneo.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testTorneo.isOficial()).isEqualTo(DEFAULT_OFICIAL);
    }

    @Test
    @Transactional
    public void createTorneoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = torneoRepository.findAll().size();

        // Create the Torneo with an existing ID
        torneo.setId(1L);
        TorneoDTO torneoDTO = torneoMapper.toDto(torneo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTorneoMockMvc.perform(post("/api/torneos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Torneo in the database
        List<Torneo> torneoList = torneoRepository.findAll();
        assertThat(torneoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTorneos() throws Exception {
        // Initialize the database
        torneoRepository.saveAndFlush(torneo);

        // Get all the torneoList
        restTorneoMockMvc.perform(get("/api/torneos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(torneo.getId().intValue())))
            .andExpect(jsonPath("$.[*].fecha").value(hasItem(DEFAULT_FECHA.toString())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].oficial").value(hasItem(DEFAULT_OFICIAL.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getTorneo() throws Exception {
        // Initialize the database
        torneoRepository.saveAndFlush(torneo);

        // Get the torneo
        restTorneoMockMvc.perform(get("/api/torneos/{id}", torneo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(torneo.getId().intValue()))
            .andExpect(jsonPath("$.fecha").value(DEFAULT_FECHA.toString()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.oficial").value(DEFAULT_OFICIAL.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTorneo() throws Exception {
        // Get the torneo
        restTorneoMockMvc.perform(get("/api/torneos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTorneo() throws Exception {
        // Initialize the database
        torneoRepository.saveAndFlush(torneo);

        int databaseSizeBeforeUpdate = torneoRepository.findAll().size();

        // Update the torneo
        Torneo updatedTorneo = torneoRepository.findById(torneo.getId()).get();
        // Disconnect from session so that the updates on updatedTorneo are not directly saved in db
        em.detach(updatedTorneo);
        updatedTorneo
            .fecha(UPDATED_FECHA)
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .nombre(UPDATED_NOMBRE)
            .oficial(UPDATED_OFICIAL);
        TorneoDTO torneoDTO = torneoMapper.toDto(updatedTorneo);

        restTorneoMockMvc.perform(put("/api/torneos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneoDTO)))
            .andExpect(status().isOk());

        // Validate the Torneo in the database
        List<Torneo> torneoList = torneoRepository.findAll();
        assertThat(torneoList).hasSize(databaseSizeBeforeUpdate);
        Torneo testTorneo = torneoList.get(torneoList.size() - 1);
        assertThat(testTorneo.getFecha()).isEqualTo(UPDATED_FECHA);
        assertThat(testTorneo.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testTorneo.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testTorneo.isOficial()).isEqualTo(UPDATED_OFICIAL);
    }

    @Test
    @Transactional
    public void updateNonExistingTorneo() throws Exception {
        int databaseSizeBeforeUpdate = torneoRepository.findAll().size();

        // Create the Torneo
        TorneoDTO torneoDTO = torneoMapper.toDto(torneo);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTorneoMockMvc.perform(put("/api/torneos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(torneoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Torneo in the database
        List<Torneo> torneoList = torneoRepository.findAll();
        assertThat(torneoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTorneo() throws Exception {
        // Initialize the database
        torneoRepository.saveAndFlush(torneo);

        int databaseSizeBeforeDelete = torneoRepository.findAll().size();

        // Delete the torneo
        restTorneoMockMvc.perform(delete("/api/torneos/{id}", torneo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Torneo> torneoList = torneoRepository.findAll();
        assertThat(torneoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
