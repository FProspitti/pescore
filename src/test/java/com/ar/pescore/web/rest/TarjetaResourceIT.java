package com.ar.pescore.web.rest;

import com.ar.pescore.PescoreApp;
import com.ar.pescore.domain.Tarjeta;
import com.ar.pescore.repository.TarjetaRepository;
import com.ar.pescore.service.TarjetaService;
import com.ar.pescore.service.dto.TarjetaDTO;
import com.ar.pescore.service.mapper.TarjetaMapper;
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
import java.time.Duration;
import java.util.List;

import static com.ar.pescore.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link TarjetaResource} REST controller.
 */
@SpringBootTest(classes = PescoreApp.class)
public class TarjetaResourceIT {

    private static final Integer DEFAULT_CANTIDAD = 1;
    private static final Integer UPDATED_CANTIDAD = 2;

    private static final Float DEFAULT_PESO = 1F;
    private static final Float UPDATED_PESO = 2F;

    private static final Duration DEFAULT_HORA = Duration.ofHours(6);
    private static final Duration UPDATED_HORA = Duration.ofHours(12);

    private static final Float DEFAULT_PUNTAJE = 1F;
    private static final Float UPDATED_PUNTAJE = 2F;

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private TarjetaMapper tarjetaMapper;

    @Autowired
    private TarjetaService tarjetaService;

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

    private MockMvc restTarjetaMockMvc;

    private Tarjeta tarjeta;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TarjetaResource tarjetaResource = new TarjetaResource(tarjetaService);
        this.restTarjetaMockMvc = MockMvcBuilders.standaloneSetup(tarjetaResource)
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
    public static Tarjeta createEntity(EntityManager em) {
        Tarjeta tarjeta = new Tarjeta()
            .cantidad(DEFAULT_CANTIDAD)
            .peso(DEFAULT_PESO)
            .hora(DEFAULT_HORA)
            .puntaje(DEFAULT_PUNTAJE);
        return tarjeta;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Tarjeta createUpdatedEntity(EntityManager em) {
        Tarjeta tarjeta = new Tarjeta()
            .cantidad(UPDATED_CANTIDAD)
            .peso(UPDATED_PESO)
            .hora(UPDATED_HORA)
            .puntaje(UPDATED_PUNTAJE);
        return tarjeta;
    }

    @BeforeEach
    public void initTest() {
        tarjeta = createEntity(em);
    }

    @Test
    @Transactional
    public void createTarjeta() throws Exception {
        int databaseSizeBeforeCreate = tarjetaRepository.findAll().size();

        // Create the Tarjeta
        TarjetaDTO tarjetaDTO = tarjetaMapper.toDto(tarjeta);
        restTarjetaMockMvc.perform(post("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjetaDTO)))
            .andExpect(status().isCreated());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeCreate + 1);
        Tarjeta testTarjeta = tarjetaList.get(tarjetaList.size() - 1);
        assertThat(testTarjeta.getCantidad()).isEqualTo(DEFAULT_CANTIDAD);
        assertThat(testTarjeta.getPeso()).isEqualTo(DEFAULT_PESO);
        assertThat(testTarjeta.getHora()).isEqualTo(DEFAULT_HORA);
        assertThat(testTarjeta.getPuntaje()).isEqualTo(DEFAULT_PUNTAJE);
    }

    @Test
    @Transactional
    public void createTarjetaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tarjetaRepository.findAll().size();

        // Create the Tarjeta with an existing ID
        tarjeta.setId(1L);
        TarjetaDTO tarjetaDTO = tarjetaMapper.toDto(tarjeta);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTarjetaMockMvc.perform(post("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjetaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTarjetas() throws Exception {
        // Initialize the database
        tarjetaRepository.saveAndFlush(tarjeta);

        // Get all the tarjetaList
        restTarjetaMockMvc.perform(get("/api/tarjetas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tarjeta.getId().intValue())))
            .andExpect(jsonPath("$.[*].cantidad").value(hasItem(DEFAULT_CANTIDAD)))
            .andExpect(jsonPath("$.[*].peso").value(hasItem(DEFAULT_PESO.doubleValue())))
            .andExpect(jsonPath("$.[*].hora").value(hasItem(DEFAULT_HORA.toString())))
            .andExpect(jsonPath("$.[*].puntaje").value(hasItem(DEFAULT_PUNTAJE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getTarjeta() throws Exception {
        // Initialize the database
        tarjetaRepository.saveAndFlush(tarjeta);

        // Get the tarjeta
        restTarjetaMockMvc.perform(get("/api/tarjetas/{id}", tarjeta.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tarjeta.getId().intValue()))
            .andExpect(jsonPath("$.cantidad").value(DEFAULT_CANTIDAD))
            .andExpect(jsonPath("$.peso").value(DEFAULT_PESO.doubleValue()))
            .andExpect(jsonPath("$.hora").value(DEFAULT_HORA.toString()))
            .andExpect(jsonPath("$.puntaje").value(DEFAULT_PUNTAJE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTarjeta() throws Exception {
        // Get the tarjeta
        restTarjetaMockMvc.perform(get("/api/tarjetas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTarjeta() throws Exception {
        // Initialize the database
        tarjetaRepository.saveAndFlush(tarjeta);

        int databaseSizeBeforeUpdate = tarjetaRepository.findAll().size();

        // Update the tarjeta
        Tarjeta updatedTarjeta = tarjetaRepository.findById(tarjeta.getId()).get();
        // Disconnect from session so that the updates on updatedTarjeta are not directly saved in db
        em.detach(updatedTarjeta);
        updatedTarjeta
            .cantidad(UPDATED_CANTIDAD)
            .peso(UPDATED_PESO)
            .hora(UPDATED_HORA)
            .puntaje(UPDATED_PUNTAJE);
        TarjetaDTO tarjetaDTO = tarjetaMapper.toDto(updatedTarjeta);

        restTarjetaMockMvc.perform(put("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjetaDTO)))
            .andExpect(status().isOk());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeUpdate);
        Tarjeta testTarjeta = tarjetaList.get(tarjetaList.size() - 1);
        assertThat(testTarjeta.getCantidad()).isEqualTo(UPDATED_CANTIDAD);
        assertThat(testTarjeta.getPeso()).isEqualTo(UPDATED_PESO);
        assertThat(testTarjeta.getHora()).isEqualTo(UPDATED_HORA);
        assertThat(testTarjeta.getPuntaje()).isEqualTo(UPDATED_PUNTAJE);
    }

    @Test
    @Transactional
    public void updateNonExistingTarjeta() throws Exception {
        int databaseSizeBeforeUpdate = tarjetaRepository.findAll().size();

        // Create the Tarjeta
        TarjetaDTO tarjetaDTO = tarjetaMapper.toDto(tarjeta);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTarjetaMockMvc.perform(put("/api/tarjetas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tarjetaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Tarjeta in the database
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTarjeta() throws Exception {
        // Initialize the database
        tarjetaRepository.saveAndFlush(tarjeta);

        int databaseSizeBeforeDelete = tarjetaRepository.findAll().size();

        // Delete the tarjeta
        restTarjetaMockMvc.perform(delete("/api/tarjetas/{id}", tarjeta.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Tarjeta> tarjetaList = tarjetaRepository.findAll();
        assertThat(tarjetaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
