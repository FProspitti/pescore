package com.ar.pescore.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class SubCategoriaMapperTest {

    private SubCategoriaMapper subCategoriaMapper;

    @BeforeEach
    public void setUp() {
        subCategoriaMapper = new SubCategoriaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(subCategoriaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(subCategoriaMapper.fromId(null)).isNull();
    }
}
