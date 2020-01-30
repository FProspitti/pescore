package com.ar.pescore.service.dto;
import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ar.pescore.domain.Campeonato} entity.
 */
public class CampeonatoDTO implements Serializable {

    private Long id;

    private LocalDate fechaCreacion;

    @Size(max = 50)
    private String nombre;

    private Integer cantidadPescadores;

    private Integer cantidadClubes;


    private Long categoriaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadPescadores() {
        return cantidadPescadores;
    }

    public void setCantidadPescadores(Integer cantidadPescadores) {
        this.cantidadPescadores = cantidadPescadores;
    }

    public Integer getCantidadClubes() {
        return cantidadClubes;
    }

    public void setCantidadClubes(Integer cantidadClubes) {
        this.cantidadClubes = cantidadClubes;
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

        CampeonatoDTO campeonatoDTO = (CampeonatoDTO) o;
        if (campeonatoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), campeonatoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CampeonatoDTO{" +
            "id=" + getId() +
            ", fechaCreacion='" + getFechaCreacion() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", cantidadPescadores=" + getCantidadPescadores() +
            ", cantidadClubes=" + getCantidadClubes() +
            ", categoriaId=" + getCategoriaId() +
            "}";
    }
}
