package org.latacunga.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class CuentaDto implements Serializable {
    private Long id;
    private String codigo;
    private String descripcion;
    private Integer nivel;
    private Integer grupo;
    private Integer secuencia;
    private String tipo;
    private Boolean transaccionReciproca;
    private Boolean aperturaInicial;
    private String itemDebito;
    private String itemCredito;
    private BigDecimal debito;
    private BigDecimal credito;
    private String estado;

    public CuentaDto() {
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
}
