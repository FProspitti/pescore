package com.ar.pescore.repository;

import com.ar.pescore.domain.Torneo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Torneo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TorneoRepository extends JpaRepository<Torneo, Long> {

}
