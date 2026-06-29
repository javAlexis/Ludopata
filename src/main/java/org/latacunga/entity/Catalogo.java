package org.latacunga.entity;

import org.latacunga.util.EsquemaConfig;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "catalogo", schema = EsquemaConfig.capacitacion)
public class Catalogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String descripcion;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "usuario_edicion")
    private String usuarioEdicion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEdicion;

    public Catalogo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaEdicion() {
        return fechaEdicion;
    }

    public void setFechaEdicion(Date fechaEdicion) {
        this.fechaEdicion = fechaEdicion;
    }

    public String getUsuarioEdicion() {
        return usuarioEdicion;
    }

    public void setUsuarioEdicion(String usuarioEdicion) {
        this.usuarioEdicion = usuarioEdicion;
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaEdicion=" + fechaEdicion +
                '}';
    }
}
