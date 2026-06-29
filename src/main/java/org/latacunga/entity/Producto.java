package org.latacunga.entity;

import org.latacunga.util.EsquemaConfig;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "producto", schema = EsquemaConfig.capacitacion)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    @Column(name = "tipo_medida")
    private String tipoMedida;
    private BigDecimal stock;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_catalogo", referencedColumnName = "id")
    private Catalogo catalogo;
    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "usuario_edicion")
    private String usuarioEdicion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEdicion;

    public Producto() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public BigDecimal getStock() {
        return stock;
    }

    public void setStock(BigDecimal stock) {
        this.stock = stock;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
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

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", estado='" + estado + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", tipoMedida='" + tipoMedida + '\'' +
                ", stock=" + stock +
                ", catalogo=" + catalogo +
                ", usuarioCreacion='" + usuarioCreacion + '\'' +
                ", usuarioEdicion='" + usuarioEdicion + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaEdicion=" + fechaEdicion +
                '}';
    }
}
