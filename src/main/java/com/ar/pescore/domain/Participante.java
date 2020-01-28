package com.ar.pescore.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Participante.
 */
@Entity
@Table(name = "participante")
public class Participante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Size(max = 50)
    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "edad")
    private Integer edad;

    @OneToMany(mappedBy = "participante")
    private Set<Tarjeta> tarjetas = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "participante_campeonato",
               joinColumns = @JoinColumn(name = "participante_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "campeonato_id", referencedColumnName = "id"))
    private Set<Campeonato> campeonatoes = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("participantes")
    private Club club;

    @ManyToOne
    @JsonIgnoreProperties("participantes")
    private Categoria categoria;

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

    public Participante nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Participante apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public Participante fechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public Participante edad(Integer edad) {
        this.edad = edad;
        return this;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Set<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public Participante tarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
        return this;
    }

    public Participante addTarjeta(Tarjeta tarjeta) {
        this.tarjetas.add(tarjeta);
        tarjeta.setParticipante(this);
        return this;
    }

    public Participante removeTarjeta(Tarjeta tarjeta) {
        this.tarjetas.remove(tarjeta);
        tarjeta.setParticipante(null);
        return this;
    }

    public void setTarjetas(Set<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public Set<Campeonato> getCampeonatoes() {
        return campeonatoes;
    }

    public Participante campeonatoes(Set<Campeonato> campeonatoes) {
        this.campeonatoes = campeonatoes;
        return this;
    }

    public Participante addCampeonato(Campeonato campeonato) {
        this.campeonatoes.add(campeonato);
        campeonato.getParticipantes().add(this);
        return this;
    }

    public Participante removeCampeonato(Campeonato campeonato) {
        this.campeonatoes.remove(campeonato);
        campeonato.getParticipantes().remove(this);
        return this;
    }

    public void setCampeonatoes(Set<Campeonato> campeonatoes) {
        this.campeonatoes = campeonatoes;
    }

    public Club getClub() {
        return club;
    }

    public Participante club(Club club) {
        this.club = club;
        return this;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Participante categoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Participante)) {
            return false;
        }
        return id != null && id.equals(((Participante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Participante{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", apellido='" + getApellido() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            ", edad=" + getEdad() +
            "}";
    }
}
