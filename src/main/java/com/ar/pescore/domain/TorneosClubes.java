package com.ar.pescore.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A TorneosClubes.
 */
@Entity
@Table(name = "torneos_clubes")
public class TorneosClubes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "posicion")
    private Integer posicion;

    @Column(name = "puntaje")
    private Integer puntaje;

    @Column(name = "puntos")
    private Integer puntos;

    @Column(name = "fecha")
    private LocalDate fecha;

    @OneToMany(mappedBy = "torneosClubes")
    private Set<Torneo> torneos = new HashSet<>();

    @OneToMany(mappedBy = "torneosClubes")
    private Set<Club> clubs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public TorneosClubes posicion(Integer posicion) {
        this.posicion = posicion;
        return this;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public TorneosClubes puntaje(Integer puntaje) {
        this.puntaje = puntaje;
        return this;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public TorneosClubes puntos(Integer puntos) {
        this.puntos = puntos;
        return this;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public TorneosClubes fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Set<Torneo> getTorneos() {
        return torneos;
    }

    public TorneosClubes torneos(Set<Torneo> torneos) {
        this.torneos = torneos;
        return this;
    }

    public TorneosClubes addTorneo(Torneo torneo) {
        this.torneos.add(torneo);
        torneo.setTorneosClubes(this);
        return this;
    }

    public TorneosClubes removeTorneo(Torneo torneo) {
        this.torneos.remove(torneo);
        torneo.setTorneosClubes(null);
        return this;
    }

    public void setTorneos(Set<Torneo> torneos) {
        this.torneos = torneos;
    }

    public Set<Club> getClubs() {
        return clubs;
    }

    public TorneosClubes clubs(Set<Club> clubs) {
        this.clubs = clubs;
        return this;
    }

    public TorneosClubes addClub(Club club) {
        this.clubs.add(club);
        club.setTorneosClubes(this);
        return this;
    }

    public TorneosClubes removeClub(Club club) {
        this.clubs.remove(club);
        club.setTorneosClubes(null);
        return this;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TorneosClubes)) {
            return false;
        }
        return id != null && id.equals(((TorneosClubes) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TorneosClubes{" +
            "id=" + getId() +
            ", posicion=" + getPosicion() +
            ", puntaje=" + getPuntaje() +
            ", puntos=" + getPuntos() +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
