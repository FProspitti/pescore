package com.ar.pescore.web.rest;

import com.ar.pescore.PescoreApp;
import com.ar.pescore.domain.Campeonato;
import com.ar.pescore.repository.CampeonatoRepository;
import com.ar.pescore.service.CampeonatoService;
import com.ar.pescore.service.dto.CampeonatoDTO;
import com.ar.pescore.service.mapper.CampeonatoMapper;
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
 * Integration tests for the {@link CampeonatoResource} REST controller.
 */
@SpringBootTest(classes = PescoreApp.class)
public class CampeonatoResourceIT {

    private static final LocalDate DEFAULT_FECHA_CREACION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_FECHA_CREACION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final Integer DEFAULT_CANTIDAD_PESCADORES = 1;
    private static final Integer UPDATED_CANTIDAD_PESCADORES = 2;

    private static final Integer DEFAULT_CANTIDAD_CLUBES = 1;
    private static final Integer UPDATED_CANTIDAD_CLUBES = 2;

    @Autowired
    private CampeonatoRepository campeonatoRepository;

    @Autowired
    private CampeonatoMapper campeonatoMapper;

    @Autowired
    private CampeonatoService campeonatoService;

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

    private MockMvc restCampeonatoMockMvc;

    private Campeonato campeonato;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CampeonatoResource campeonatoResource = new CampeonatoResource(campeonatoService);
        this.restCampeonatoMockMvc = MockMvcBuilders.standaloneSetup(campeonatoResource)
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
    public static Campeonato createEntity(EntityManager em) {
        Campeonato campeonato = new Campeonato()
            .fechaCreacion(DEFAULT_FECHA_CREACION)
            .nombre(DEFAULT_NOMBRE)
            .cantidadPescadores(DEFAULT_CANTIDAD_PESCADORES)
            .cantidadClubes(DEFAULT_CANTIDAD_CLUBES);
        return campeonato;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campeonato createUpdatedEntity(EntityManager em) {
        Campeonato campeonato = new Campeonato()
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .nombre(UPDATED_NOMBRE)
            .cantidadPescadores(UPDATED_CANTIDAD_PESCADORES)
            .cantidadClubes(UPDATED_CANTIDAD_CLUBES);
        return campeonato;
    }

    @BeforeEach
    public void initTest() {
        campeonato = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampeonato() throws Exception {
        int databaseSizeBeforeCreate = campeonatoRepository.findAll().size();

        // Create the Campeonato
        CampeonatoDTO campeonatoDTO = campeonatoMapper.toDto(campeonato);
        restCampeonatoMockMvc.perform(post("/api/campeonatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campeonatoDTO)))
            .andExpect(status().isCreated());

        // Validate the Campeonato in the database
        List<Campeonato> campeonatoList = campeonatoRepository.findAll();
        assertThat(campeonatoList).hasSize(databaseSizeBeforeCreate + 1);
        Campeonato testCampeonato = campeonatoList.get(campeonatoList.size() - 1);
        assertThat(testCampeonato.getFechaCreacion()).isEqualTo(DEFAULT_FECHA_CREACION);
        assertThat(testCampeonato.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testCampeonato.getCantidadPescadores()).isEqualTo(DEFAULT_CANTIDAD_PESCADORES);
        assertThat(testCampeonato.getCantidadClubes()).isEqualTo(DEFAULT_CANTIDAD_CLUBES);
    }

    @Test
    @Transactional
    public void createCampeonatoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campeonatoRepository.findAll().size();

        // Create the Campeonato with an existing ID
        campeonato.setId(1L);
        CampeonatoDTO campeonatoDTO = campeonatoMapper.toDto(campeonato);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampeonatoMockMvc.perform(post("/api/campeonatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campeonatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Campeonato in the database
        List<Campeonato> campeonatoList = campeonatoRepository.findAll();
        assertThat(campeonatoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampeonatoes() throws Exception {
        // Initialize the database
        campeonatoRepository.saveAndFlush(campeonato);

        // Get all the campeonatoList
        restCampeonatoMockMvc.perform(get("/api/campeonatoes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campeonato.getId().intValue())))
            .andExpect(jsonPath("$.[*].fechaCreacion").value(hasItem(DEFAULT_FECHA_CREACION.toString())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE)))
            .andExpect(jsonPath("$.[*].cantidadPescadores").value(hasItem(DEFAULT_CANTIDAD_PESCADORES)))
            .andExpect(jsonPath("$.[*].cantidadClubes").value(hasItem(DEFAULT_CANTIDAD_CLUBES)));
    }
    
    @Test
    @Transactional
    public void getCampeonato() throws Exception {
        // Initialize the database
        campeonatoRepository.saveAndFlush(campeonato);

        // Get the campeonato
        restCampeonatoMockMvc.perform(get("/api/campeonatoes/{id}", campeonato.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(campeonato.getId().intValue()))
            .andExpect(jsonPath("$.fechaCreacion").value(DEFAULT_FECHA_CREACION.toString()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE))
            .andExpect(jsonPath("$.cantidadPescadores").value(DEFAULT_CANTIDAD_PESCADORES))
            .andExpect(jsonPath("$.cantidadClubes").value(DEFAULT_CANTIDAD_CLUBES));
    }

    @Test
    @Transactional
    public void getNonExistingCampeonato() throws Exception {
        // Get the campeonato
        restCampeonatoMockMvc.perform(get("/api/campeonatoes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampeonato() throws Exception {
        // Initialize the database
        campeonatoRepository.saveAndFlush(campeonato);

        int databaseSizeBeforeUpdate = campeonatoRepository.findAll().size();

        // Update the campeonato
        Campeonato updatedCampeonato = campeonatoRepository.findById(campeonato.getId()).get();
        // Disconnect from session so that the updates on updatedCampeonato are not directly saved in db
        em.detach(updatedCampeonato);
        updatedCampeonato
            .fechaCreacion(UPDATED_FECHA_CREACION)
            .nombre(UPDATED_NOMBRE)
            .cantidadPescadores(UPDATED_CANTIDAD_PESCADORES)
            .cantidadClubes(UPDATED_CANTIDAD_CLUBES);
        CampeonatoDTO campeonatoDTO = campeonatoMapper.toDto(updatedCampeonato);

        restCampeonatoMockMvc.perform(put("/api/campeonatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campeonatoDTO)))
            .andExpect(status().isOk());

        // Validate the Campeonato in the database
        List<Campeonato> campeonatoList = campeonatoRepository.findAll();
        assertThat(campeonatoList).hasSize(databaseSizeBeforeUpdate);
        Campeonato testCampeonato = campeonatoList.get(campeonatoList.size() - 1);
        assertThat(testCampeonato.getFechaCreacion()).isEqualTo(UPDATED_FECHA_CREACION);
        assertThat(testCampeonato.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testCampeonato.getCantidadPescadores()).isEqualTo(UPDATED_CANTIDAD_PESCADORES);
        assertThat(testCampeonato.getCantidadClubes()).isEqualTo(UPDATED_CANTIDAD_CLUBES);
    }

    @Test
    @Transactional
    public void updateNonExistingCampeonato() throws Exception {
        int databaseSizeBeforeUpdate = campeonatoRepository.findAll().size();

        // Create the Campeonato
        CampeonatoDTO campeonatoDTO = campeonatoMapper.toDto(campeonato);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampeonatoMockMvc.perform(put("/api/campeonatoes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(campeonatoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Campeonato in the database
        List<Campeonato> campeonatoList = campeonatoRepository.findAll();
        assertThat(campeonatoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampeonato() throws Exception {
        // Initialize the database
        campeonatoRepository.saveAndFlush(campeonato);

        int databaseSizeBeforeDelete = campeonatoRepository.findAll().size();

        // Delete the campeonato
        restCampeonatoMockMvc.perform(delete("/api/campeonatoes/{id}", campeonato.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Campeonato> campeonatoList = campeonatoRepository.findAll();
        assertThat(campeonatoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
