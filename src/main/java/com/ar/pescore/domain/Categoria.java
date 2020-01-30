package com.ar.pescore.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Categoria.
 */
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @OneToMany(mappedBy = "categoria")
    private Set<SubCategoria> subCategorias = new HashSet<>();

    @OneToMany(mappedBy = "categoria")
    private Set<Campeonato> campeonatoes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Categoria nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<SubCategoria> getSubCategorias() {
        return subCategorias;
    }

    public Categoria subCategorias(Set<SubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
        return this;
    }

    public Categoria addSubCategoria(SubCategoria subCategoria) {
        this.subCategorias.add(subCategoria);
        subCategoria.setCategoria(this);
        return this;
    }

    public Categoria removeSubCategoria(SubCategoria subCategoria) {
        this.subCategorias.remove(subCategoria);
        subCategoria.setCategoria(null);
        return this;
    }

    public void setSubCategorias(Set<SubCategoria> subCategorias) {
        this.subCategorias = subCategorias;
    }

    public Set<Campeonato> getCampeonatoes() {
        return campeonatoes;
    }

    public Categoria campeonatoes(Set<Campeonato> campeonatoes) {
        this.campeonatoes = campeonatoes;
        return this;
    }

    public Categoria addCampeonato(Campeonato campeonato) {
        this.campeonatoes.add(campeonato);
        campeonato.setCategoria(this);
        return this;
    }

    public Categoria removeCampeonato(Campeonato campeonato) {
        this.campeonatoes.remove(campeonato);
        campeonato.setCategoria(null);
        return this;
    }

    public void setCampeonatoes(Set<Campeonato> campeonatoes) {
        this.campeonatoes = campeonatoes;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Categoria)) {
            return false;
        }
        return id != null && id.equals(((Categoria) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Categoria{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
