package com.example.bibliotecaspringboot.models.entities;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "historico", schema = "BIBLIOTECA")
public class EntidadHistorico {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idHistorico", nullable = false)
    private int idHistorico;
    @Basic
    @Column(name = "user", nullable = true, length = -1)
    private String user;
    @Basic
    @Column(name = "fecha", nullable = true)
    private Timestamp fecha;
    @Basic
    @Column(name = "info", nullable = true, length = -1)
    private String info;

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadHistorico that = (EntidadHistorico) o;

        if (idHistorico != that.idHistorico) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHistorico;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }
}
