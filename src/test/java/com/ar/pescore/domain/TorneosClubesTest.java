package com.ar.pescore.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ar.pescore.web.rest.TestUtil;

public class TorneosClubesTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TorneosClubes.class);
        TorneosClubes torneosClubes1 = new TorneosClubes();
        torneosClubes1.setId(1L);
        TorneosClubes torneosClubes2 = new TorneosClubes();
        torneosClubes2.setId(torneosClubes1.getId());
        assertThat(torneosClubes1).isEqualTo(torneosClubes2);
        torneosClubes2.setId(2L);
        assertThat(torneosClubes1).isNotEqualTo(torneosClubes2);
        torneosClubes1.setId(null);
        assertThat(torneosClubes1).isNotEqualTo(torneosClubes2);
    }
}
