package com.ar.pescore.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ar.pescore.web.rest.TestUtil;

public class TorneosClubesDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TorneosClubesDTO.class);
        TorneosClubesDTO torneosClubesDTO1 = new TorneosClubesDTO();
        torneosClubesDTO1.setId(1L);
        TorneosClubesDTO torneosClubesDTO2 = new TorneosClubesDTO();
        assertThat(torneosClubesDTO1).isNotEqualTo(torneosClubesDTO2);
        torneosClubesDTO2.setId(torneosClubesDTO1.getId());
        assertThat(torneosClubesDTO1).isEqualTo(torneosClubesDTO2);
        torneosClubesDTO2.setId(2L);
        assertThat(torneosClubesDTO1).isNotEqualTo(torneosClubesDTO2);
        torneosClubesDTO1.setId(null);
        assertThat(torneosClubesDTO1).isNotEqualTo(torneosClubesDTO2);
    }
}
