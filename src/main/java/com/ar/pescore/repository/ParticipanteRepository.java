package com.ar.pescore.repository;

import com.ar.pescore.domain.Participante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Participante entity.
 */
@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {

    @Query(value = "select distinct participante from Participante participante left join fetch participante.campeonatoes",
        countQuery = "select count(distinct participante) from Participante participante")
    Page<Participante> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct participante from Participante participante left join fetch participante.campeonatoes")
    List<Participante> findAllWithEagerRelationships();

    @Query("select participante from Participante participante left join fetch participante.campeonatoes where participante.id =:id")
    Optional<Participante> findOneWithEagerRelationships(@Param("id") Long id);

}
