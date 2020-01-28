package com.ar.pescore.service.dto;
import java.time.Duration;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ar.pescore.domain.Tarjeta} entity.
 */
public class TarjetaDTO implements Serializable {

    private Long id;

    private Integer cantidad;

    private Float peso;

    private Duration hora;

    private Float puntaje;


    private Long torneoId;

    private Long participanteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Duration getHora() {
        return hora;
    }

    public void setHora(Duration hora) {
        this.hora = hora;
    }

    public Float getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Float puntaje) {
        this.puntaje = puntaje;
    }

    public Long getTorneoId() {
        return torneoId;
    }

    public void setTorneoId(Long torneoId) {
        this.torneoId = torneoId;
    }

    public Long getParticipanteId() {
        return participanteId;
    }

    public void setParticipanteId(Long participanteId) {
        this.participanteId = participanteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TarjetaDTO tarjetaDTO = (TarjetaDTO) o;
        if (tarjetaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tarjetaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TarjetaDTO{" +
            "id=" + getId() +
            ", cantidad=" + getCantidad() +
            ", peso=" + getPeso() +
            ", hora='" + getHora() + "'" +
            ", puntaje=" + getPuntaje() +
            ", torneoId=" + getTorneoId() +
            ", participanteId=" + getParticipanteId() +
            "}";
    }
}
