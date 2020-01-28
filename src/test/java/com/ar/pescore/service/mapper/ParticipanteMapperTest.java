package com.ar.pescore.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ParticipanteMapperTest {

    private ParticipanteMapper participanteMapper;

    @BeforeEach
    public void setUp() {
        participanteMapper = new ParticipanteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(participanteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(participanteMapper.fromId(null)).isNull();
    }
}
