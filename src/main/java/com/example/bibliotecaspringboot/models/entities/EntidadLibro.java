package com.example.bibliotecaspringboot.models.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "libro", schema = "BIBLIOTECA")
public class EntidadLibro {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = true, length = -1)
    private String nombre;
    @Basic
    @Column(name = "autor", nullable = true, length = -1)
    private String autor;
    @Basic
    @Column(name = "editorial", nullable = true, length = -1)
    private String editorial;

    @ManyToOne
    @JoinColumn(name = "categoria", referencedColumnName = "id")
    private EntidadCategoria categoria;
    @OneToMany(mappedBy = "libro")
    private Collection<EntidadPrestamo> prestamosById;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public EntidadCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(EntidadCategoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadLibro that = (EntidadLibro) o;
        return id == that.id && Objects.equals(nombre, that.nombre) && Objects.equals(autor, that.autor) && Objects.equals(editorial, that.editorial) && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, autor, editorial, categoria);
    }



    public Collection<EntidadPrestamo> getPrestamosById() {
        return prestamosById;
    }

    public void setPrestamosById(Collection<EntidadPrestamo> prestamosById) {
        this.prestamosById = prestamosById;
    }
}
