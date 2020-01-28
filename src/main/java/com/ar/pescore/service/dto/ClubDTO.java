package com.ar.pescore.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ar.pescore.domain.Club} entity.
 */
public class ClubDTO implements Serializable {

    private Long id;

    @Size(max = 50)
    private String nombre;

    @Size(max = 3)
    private String abreviacion;


    private Long torneosClubesId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
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

        ClubDTO clubDTO = (ClubDTO) o;
        if (clubDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clubDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ClubDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", abreviacion='" + getAbreviacion() + "'" +
            ", torneosClubesId=" + getTorneosClubesId() +
            "}";
    }
}
