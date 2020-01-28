package com.ar.pescore.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TorneoMapperTest {

    private TorneoMapper torneoMapper;

    @BeforeEach
    public void setUp() {
        torneoMapper = new TorneoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(torneoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(torneoMapper.fromId(null)).isNull();
    }
}
