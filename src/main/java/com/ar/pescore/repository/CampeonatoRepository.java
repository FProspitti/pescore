package com.ar.pescore.repository;

import com.ar.pescore.domain.Campeonato;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Campeonato entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {

}
