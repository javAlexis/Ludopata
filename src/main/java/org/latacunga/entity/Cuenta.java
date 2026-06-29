package org.latacunga.entity;

import org.latacunga.util.EsquemaConfig;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "cuenta", schema = EsquemaConfig.capacitacion)
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;
    
    @Column(nullable = false)
    private String descripcion;
    
    @Column(nullable = false)
    private Integer nivel;

    private Integer grupo;
    private Integer secuencia;
    private String tipo; // 'A' (Agrupación/Mayor) o 'M' (Movimiento)

    @Column(name = "transaccion_reciproca")
    private Boolean transaccionReciproca;

    @Column(name = "apertura_inicial")
    private Boolean aperturaInicial;

    @Column(name = "item_debito")
    private String itemDebito;

    @Column(name = "item_credito")
    private String itemCredito;

    private BigDecimal debito;
    private BigDecimal credito;
    private String estado;

    @Column(name = "usuario_creacion")
    private String usuarioCreacion;
    @Column(name = "usuario_edicion")
    private String usuarioEdicion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_edicion")
    private Date fechaEdicion;

    public Cuenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getGrupo() {
        return grupo;
    }

    public void setGrupo(Integer grupo) {
        this.grupo = grupo;
    }

    public Integer getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(Integer secuencia) {
        this.secuencia = secuencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Boolean getTransaccionReciproca() {
        return transaccionReciproca;
    }

    public void setTransaccionReciproca(Boolean transaccionReciproca) {
        this.transaccionReciproca = transaccionReciproca;
    }

    public Boolean getAperturaInicial() {
        return aperturaInicial;
    }

    public void setAperturaInicial(Boolean aperturaInicial) {
        this.aperturaInicial = aperturaInicial;
    }

    public String getItemDebito() {
        return itemDebito;
    }

    public void setItemDebito(String itemDebito) {
        this.itemDebito = itemDebito;
    }

    public String getItemCredito() {
        return itemCredito;
    }

    public void setItemCredito(String itemCredito) {
        this.itemCredito = itemCredito;
    }

    public BigDecimal getDebito() {
        return debito;
    }

    public void setDebito(BigDecimal debito) {
        this.debito = debito;
    }

    public BigDecimal getCredito() {
        return credito;
    }

    public void setCredito(BigDecimal credito) {
        this.credito = credito;
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
}
