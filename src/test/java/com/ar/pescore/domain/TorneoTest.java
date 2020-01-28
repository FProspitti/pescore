package com.ar.pescore.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.ar.pescore.web.rest.TestUtil;

public class TorneoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Torneo.class);
        Torneo torneo1 = new Torneo();
        torneo1.setId(1L);
        Torneo torneo2 = new Torneo();
        torneo2.setId(torneo1.getId());
        assertThat(torneo1).isEqualTo(torneo2);
        torneo2.setId(2L);
        assertThat(torneo1).isNotEqualTo(torneo2);
        torneo1.setId(null);
        assertThat(torneo1).isNotEqualTo(torneo2);
    }
}
