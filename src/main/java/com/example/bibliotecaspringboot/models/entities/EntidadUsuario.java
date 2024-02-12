package com.example.bibliotecaspringboot.models.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "usuario", schema = "BIBLIOTECA")
public class EntidadUsuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = true, length = -1)
    private String nombre;
    @Basic
    @Column(name = "apellidos", nullable = true, length = -1)
    private String apellidos;
    @OneToMany(mappedBy = "usuario")
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadUsuario that = (EntidadUsuario) o;

        if (id != that.id) return false;
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) return false;
        if (apellidos != null ? !apellidos.equals(that.apellidos) : that.apellidos != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (apellidos != null ? apellidos.hashCode() : 0);
        return result;
    }

    public Collection<EntidadPrestamo> getPrestamosById() {
        return prestamosById;
    }

    public void setPrestamosById(Collection<EntidadPrestamo> prestamosById) {
        this.prestamosById = prestamosById;
    }
}
