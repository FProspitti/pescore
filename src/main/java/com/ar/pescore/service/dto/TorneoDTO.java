package com.ar.pescore.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ar.pescore.domain.Torneo} entity.
 */
public class TorneoDTO implements Serializable {

    private Long id;

    private LocalDate fecha;

    private Boolean oficial;


    private Long campeonatoId;

    private Long especialidadId;

    private Long clubId;

    private Long torneosClubesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Boolean isOficial() {
        return oficial;
    }

    public void setOficial(Boolean oficial) {
        this.oficial = oficial;
    }

    public Long getCampeonatoId() {
        return campeonatoId;
    }

    public void setCampeonatoId(Long campeonatoId) {
        this.campeonatoId = campeonatoId;
    }

    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Long getTorneosClubesId() {
        return torneosClubesId;
    }

    public void setTorneosClubesId(Long torneosClubesId) {
        this.torneosClubesId = torneosClubesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TorneoDTO torneoDTO = (TorneoDTO) o;
        if (torneoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), torneoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TorneoDTO{" +
            "id=" + getId() +
            ", fecha='" + getFecha() + "'" +
            ", oficial='" + isOficial() + "'" +
            ", campeonatoId=" + getCampeonatoId() +
            ", especialidadId=" + getEspecialidadId() +
            ", clubId=" + getClubId() +
            ", torneosClubesId=" + getTorneosClubesId() +
            "}";
    }
}
