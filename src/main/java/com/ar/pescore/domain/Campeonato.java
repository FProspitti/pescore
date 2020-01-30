package com.ar.pescore.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Campeonato.
 */
@Entity
@Table(name = "campeonato")
public class Campeonato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Column(name = "cantidad_pescadores")
    private Integer cantidadPescadores;

    @Column(name = "cantidad_clubes")
    private Integer cantidadClubes;

    @OneToMany(mappedBy = "campeonato")
    private Set<Torneo> torneos = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("campeonatoes")
    private Categoria categoria;

    @ManyToMany(mappedBy = "campeonatoes")
    @JsonIgnore
    private Set<Participante> participantes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Campeonato fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public Campeonato nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadPescadores() {
        return cantidadPescadores;
    }

    public Campeonato cantidadPescadores(Integer cantidadPescadores) {
        this.cantidadPescadores = cantidadPescadores;
        return this;
    }

    public void setCantidadPescadores(Integer cantidadPescadores) {
        this.cantidadPescadores = cantidadPescadores;
    }

    public Integer getCantidadClubes() {
        return cantidadClubes;
    }

    public Campeonato cantidadClubes(Integer cantidadClubes) {
        this.cantidadClubes = cantidadClubes;
        return this;
    }

    public void setCantidadClubes(Integer cantidadClubes) {
        this.cantidadClubes = cantidadClubes;
    }

    public Set<Torneo> getTorneos() {
        return torneos;
    }

    public Campeonato torneos(Set<Torneo> torneos) {
        this.torneos = torneos;
        return this;
    }

    public Campeonato addTorneo(Torneo torneo) {
        this.torneos.add(torneo);
        torneo.setCampeonato(this);
        return this;
    }

    public Campeonato removeTorneo(Torneo torneo) {
        this.torneos.remove(torneo);
        torneo.setCampeonato(null);
        return this;
    }

    public void setTorneos(Set<Torneo> torneos) {
        this.torneos = torneos;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Campeonato categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

    public Campeonato participantes(Set<Participante> participantes) {
        this.participantes = participantes;
        return this;
    }

    public Campeonato addParticipante(Participante participante) {
        this.participantes.add(participante);
        participante.getCampeonatoes().add(this);
        return this;
    }

    public Campeonato removeParticipante(Participante participante) {
        this.participantes.remove(participante);
        participante.getCampeonatoes().remove(this);
        return this;
    }

    public void setParticipantes(Set<Participante> participantes) {
        this.participantes = participantes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Campeonato)) {
            return false;
        }
        return id != null && id.equals(((Campeonato) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Campeonato{" +
            "id=" + getId() +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", cantidadPescadores=" + getCantidadPescadores() +
            ", cantidadClubes=" + getCantidadClubes() +
            "}";
    }
}
