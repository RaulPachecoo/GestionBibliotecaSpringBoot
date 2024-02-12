package com.example.bibliotecaspringboot.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "prestamos", schema = "BIBLIOTECA")
public class EntidadPrestamo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_prestamo", nullable = false)
    private int idPrestamo;
    @Basic
    @Column(name = "fecha_prestamo", nullable = true)
    private Timestamp fechaPrestamo;
    @ManyToOne
    @JoinColumn(name = "id_libro", referencedColumnName = "id")
    @JsonIgnoreProperties("listaPrestamos")
    private EntidadLibro libro;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    @JsonIgnoreProperties("listaPrestamos")
    private EntidadUsuario usuario;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Timestamp getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Timestamp fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadPrestamo that = (EntidadPrestamo) o;

        if (idPrestamo != that.idPrestamo) return false;
        if (fechaPrestamo != null ? !fechaPrestamo.equals(that.fechaPrestamo) : that.fechaPrestamo != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrestamo;
        result = 31 * result + (fechaPrestamo != null ? fechaPrestamo.hashCode() : 0);
        return result;
    }

    public EntidadLibro getLibro() {
        return libro;
    }

    public void setLibro(EntidadLibro libro) {
        this.libro = libro;
    }

    public EntidadUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(EntidadUsuario usuario) {
        this.usuario = usuario;
    }
}
