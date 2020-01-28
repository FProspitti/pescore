package com.ar.pescore.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Club.
 */
@Entity
@Table(name = "club")
public class Club implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Size(max = 3)
    @Column(name = "abreviacion", length = 3)
    private String abreviacion;

    @OneToMany(mappedBy = "club")
    private Set<Participante> participantes = new HashSet<>();

    @OneToMany(mappedBy = "club")
    private Set<Torneo> torneos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("clubs")
    private TorneosClubes torneosClubes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Club nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public Club abreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
        return this;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

    public Club participantes(Set<Participante> participantes) {
        this.participantes = participantes;
        return this;
    }

    public Club addParticipante(Participante participante) {
        this.participantes.add(participante);
        participante.setClub(this);
        return this;
    }

    public Club removeParticipante(Participante participante) {
        this.participantes.remove(participante);
        participante.setClub(null);
        return this;
    }

    public void setParticipantes(Set<Participante> participantes) {
        this.participantes = participantes;
    }

    public Set<Torneo> getTorneos() {
        return torneos;
    }

    public Club torneos(Set<Torneo> torneos) {
        this.torneos = torneos;
        return this;
    }

    public Club addTorneo(Torneo torneo) {
        this.torneos.add(torneo);
        torneo.setClub(this);
        return this;
    }

    public Club removeTorneo(Torneo torneo) {
        this.torneos.remove(torneo);
        torneo.setClub(null);
        return this;
    }

    public void setTorneos(Set<Torneo> torneos) {
        this.torneos = torneos;
    }

    public TorneosClubes getTorneosClubes() {
        return torneosClubes;
    }

    public Club torneosClubes(TorneosClubes torneosClubes) {
        this.torneosClubes = torneosClubes;
        return this;
    }

    public void setTorneosClubes(TorneosClubes torneosClubes) {
        this.torneosClubes = torneosClubes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Club)) {
            return false;
        }
        return id != null && id.equals(((Club) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Club{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", abreviacion='" + getAbreviacion() + "'" +
            "}";
    }
}
