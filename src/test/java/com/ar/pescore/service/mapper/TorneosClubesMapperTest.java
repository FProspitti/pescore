package com.ar.pescore.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TorneosClubesMapperTest {

    private TorneosClubesMapper torneosClubesMapper;

    @BeforeEach
    public void setUp() {
        torneosClubesMapper = new TorneosClubesMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(torneosClubesMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(torneosClubesMapper.fromId(null)).isNull();
    }
}
