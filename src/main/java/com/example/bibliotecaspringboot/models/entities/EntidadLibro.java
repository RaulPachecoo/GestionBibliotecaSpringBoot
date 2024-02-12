package com.example.bibliotecaspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;

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
    @JsonIgnoreProperties("listaLibros")
    private EntidadCategoria categoria;
    @OneToMany(mappedBy = "libro")
    @JsonIgnoreProperties("libro")
    private Collection<EntidadPrestamo> listaPrestamos;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadLibro that = (EntidadLibro) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (autor != null ? !autor.equals(that.autor) : that.autor != null) return false;
        if (editorial != null ? !editorial.equals(that.editorial) : that.editorial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (editorial != null ? editorial.hashCode() : 0);
        return result;
    }

    public EntidadCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(EntidadCategoria categoria) {
        this.categoria = categoria;
    }

    public Collection<EntidadPrestamo> getListaPrestamos() {
        return listaPrestamos;
    }

    public void setListaPrestamos(Collection<EntidadPrestamo> listaPrestamos) {
        this.listaPrestamos = listaPrestamos;
    }
}
