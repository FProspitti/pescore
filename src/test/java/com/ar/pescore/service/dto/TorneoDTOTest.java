package com.ar.pescore.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ar.pescore.web.rest.TestUtil;

public class TorneoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TorneoDTO.class);
        TorneoDTO torneoDTO1 = new TorneoDTO();
        torneoDTO1.setId(1L);
        TorneoDTO torneoDTO2 = new TorneoDTO();
        assertThat(torneoDTO1).isNotEqualTo(torneoDTO2);
        torneoDTO2.setId(torneoDTO1.getId());
        assertThat(torneoDTO1).isEqualTo(torneoDTO2);
        torneoDTO2.setId(2L);
        assertThat(torneoDTO1).isNotEqualTo(torneoDTO2);
        torneoDTO1.setId(null);
        assertThat(torneoDTO1).isNotEqualTo(torneoDTO2);
    }
}
