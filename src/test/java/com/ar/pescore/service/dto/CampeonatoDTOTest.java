package com.ar.pescore.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ar.pescore.web.rest.TestUtil;

public class CampeonatoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CampeonatoDTO.class);
        CampeonatoDTO campeonatoDTO1 = new CampeonatoDTO();
        campeonatoDTO1.setId(1L);
        CampeonatoDTO campeonatoDTO2 = new CampeonatoDTO();
        assertThat(campeonatoDTO1).isNotEqualTo(campeonatoDTO2);
        campeonatoDTO2.setId(campeonatoDTO1.getId());
        assertThat(campeonatoDTO1).isEqualTo(campeonatoDTO2);
        campeonatoDTO2.setId(2L);
        assertThat(campeonatoDTO1).isNotEqualTo(campeonatoDTO2);
        campeonatoDTO1.setId(null);
        assertThat(campeonatoDTO1).isNotEqualTo(campeonatoDTO2);
    }
}
