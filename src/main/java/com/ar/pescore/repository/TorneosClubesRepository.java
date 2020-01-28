package com.ar.pescore.repository;

import com.ar.pescore.domain.TorneosClubes;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TorneosClubes entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TorneosClubesRepository extends JpaRepository<TorneosClubes, Long> {

}
