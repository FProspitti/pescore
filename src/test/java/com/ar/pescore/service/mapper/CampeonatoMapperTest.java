package com.ar.pescore.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class CampeonatoMapperTest {

    private CampeonatoMapper campeonatoMapper;

    @BeforeEach
    public void setUp() {
        campeonatoMapper = new CampeonatoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(campeonatoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(campeonatoMapper.fromId(null)).isNull();
    }
}
