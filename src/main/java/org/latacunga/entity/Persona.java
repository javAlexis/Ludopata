package org.latacunga.entity;

import org.latacunga.util.EsquemaConfig;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persona", schema = EsquemaConfig.capacitacion)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cedula;
    private String nombres;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
    private String estado;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "usuario_edicion")
    private String usuarioEdicion;
    @Column(name = "usuario_eliminado")
    private String usuarioEliminado;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEdicion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEliminado;


    public Persona() {}

    //alt + ins

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    public String getUsuarioEdicion() {
        return usuarioEdicion;
    }

    public void setUsuarioEdicion(String usuarioEdicion) {
        this.usuarioEdicion = usuarioEdicion;
    }

    public String getUsuarioEliminado() {
        return usuarioEliminado;
    }

    public void setUsuarioEliminado(String usuarioEliminado) {
        this.usuarioEliminado = usuarioEliminado;
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

    public Date getFechaEliminado() {
        return fechaEliminado;
    }

    public void setFechaEliminado(Date fechaEliminado) {
        this.fechaEliminado = fechaEliminado;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estado='" + estado + '\'' +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", usuarioEdicion='" + usuarioEdicion + '\'' +
                ", usuarioEliminado='" + usuarioEliminado + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaEdicion=" + fechaEdicion +
                ", fechaEliminado=" + fechaEliminado +
                '}';
    }
}
