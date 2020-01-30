package com.ar.pescore.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Torneo.
 */
@Entity
@Table(name = "torneo")
public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "oficial")
    private Boolean oficial;

    @OneToMany(mappedBy = "torneo")
    private Set<Tarjeta> tarjetas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("torneos")
    private Campeonato campeonato;

    @ManyToOne
    @JsonIgnoreProperties("torneos")
    private Especialidad especialidad;

    @ManyToOne
    @JsonIgnoreProperties("torneos")
    private Club club;

    @ManyToOne
    @JsonIgnoreProperties("torneos")
    private TorneosClubes torneosClubes;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Torneo fecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Torneo fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Torneo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean isOficial() {
        return oficial;
    }

    public Torneo oficial(Boolean oficial) {
        this.oficial = oficial;
        return this;
    }

    public void setOficial(Boolean oficial) {
        this.oficial = oficial;
    }

    public Set<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public Torneo tarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
        return this;
    }

    public Torneo addTarjeta(Tarjeta tarjeta) {
        this.tarjetas.add(tarjeta);
        tarjeta.setTorneo(this);
        return this;
    }

    public Torneo removeTarjeta(Tarjeta tarjeta) {
        this.tarjetas.remove(tarjeta);
        tarjeta.setTorneo(null);
        return this;
    }

    public void setTarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Campeonato getCampeonato() {
        return campeonato;
    }

    public Torneo campeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
        return this;
    }

    public void setCampeonato(Campeonato campeonato) {
        this.campeonato = campeonato;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public Torneo especialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
        return this;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Club getClub() {
        return club;
    }

    public Torneo club(Club club) {
        this.club = club;
        return this;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public TorneosClubes getTorneosClubes() {
        return torneosClubes;
    }

    public Torneo torneosClubes(TorneosClubes torneosClubes) {
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
        if (!(o instanceof Torneo)) {
            return false;
        }
        return id != null && id.equals(((Torneo) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Torneo{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", oficial='" + isOficial() + "'" +
            "}";
    }
}
