package com.ar.pescore.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ar.pescore.domain.TorneosClubes} entity.
 */
public class TorneosClubesDTO implements Serializable {

    private Long id;

    private Integer posicion;

    private Integer puntaje;

    private Integer puntos;

    private LocalDate fecha;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TorneosClubesDTO torneosClubesDTO = (TorneosClubesDTO) o;
        if (torneosClubesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), torneosClubesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TorneosClubesDTO{" +
            "id=" + getId() +
            ", posicion=" + getPosicion() +
            ", puntaje=" + getPuntaje() +
            ", puntos=" + getPuntos() +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
}
