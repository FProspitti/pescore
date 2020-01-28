package com.ar.pescore.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class EspecialidadMapperTest {

    private EspecialidadMapper especialidadMapper;

    @BeforeEach
    public void setUp() {
        especialidadMapper = new EspecialidadMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(especialidadMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(especialidadMapper.fromId(null)).isNull();
    }
}
