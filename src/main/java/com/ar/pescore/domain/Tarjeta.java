package com.ar.pescore.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Duration;

/**
 * A Tarjeta.
 */
@Entity
@Table(name = "tarjeta")
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "peso")
    private Float peso;

    @Column(name = "hora")
    private Duration hora;

    @Column(name = "puntaje")
    private Float puntaje;

    @ManyToOne
    @JsonIgnoreProperties("tarjetas")
    private Torneo torneo;

    @ManyToOne
    @JsonIgnoreProperties("tarjetas")
    private Participante participante;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Tarjeta cantidad(Integer cantidad) {
        this.cantidad = cantidad;
        return this;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPeso() {
        return peso;
    }

    public Tarjeta peso(Float peso) {
        this.peso = peso;
        return this;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Duration getHora() {
        return hora;
    }

    public Tarjeta hora(Duration hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(Duration hora) {
        this.hora = hora;
    }

    public Float getPuntaje() {
        return puntaje;
    }

    public Tarjeta puntaje(Float puntaje) {
        this.puntaje = puntaje;
        return this;
    }

    public void setPuntaje(Float puntaje) {
        this.puntaje = puntaje;
    }

    public Torneo getTorneo() {
        return torneo;
    }

    public Tarjeta torneo(Torneo torneo) {
        this.torneo = torneo;
        return this;
    }

    public void setTorneo(Torneo torneo) {
        this.torneo = torneo;
    }

    public Participante getParticipante() {
        return participante;
    }

    public Tarjeta participante(Participante participante) {
        this.participante = participante;
        return this;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tarjeta)) {
            return false;
        }
        return id != null && id.equals(((Tarjeta) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
            "id=" + getId() +
            ", cantidad=" + getCantidad() +
            ", peso=" + getPeso() +
            ", hora='" + getHora() + "'" +
            ", puntaje=" + getPuntaje() +
            "}";
    }
}
