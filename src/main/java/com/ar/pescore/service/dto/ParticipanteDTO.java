package com.ar.pescore.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.ar.pescore.domain.Participante} entity.
 */
public class ParticipanteDTO implements Serializable {

    private Long id;

    @Size(max = 50)
    private String nombre;

    @Size(max = 50)
    private String apellido;

    private LocalDate fechaNacimiento;

    private Integer edad;


    private Set<CampeonatoDTO> campeonatoes = new HashSet<>();

    private Long clubId;

    private Long categoriaId;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Set<CampeonatoDTO> getCampeonatoes() {
        return campeonatoes;
    }

    public void setCampeonatoes(Set<CampeonatoDTO> campeonatoes) {
        this.campeonatoes = campeonatoes;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ParticipanteDTO participanteDTO = (ParticipanteDTO) o;
        if (participanteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), participanteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ParticipanteDTO{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", edad=" + getEdad() +
            ", clubId=" + getClubId() +
            ", categoriaId=" + getCategoriaId() +
            "}";
    }
}
