package org.latacunga.entity;

import org.latacunga.util.EsquemaConfig;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nivel_cuenta", schema = EsquemaConfig.capacitacion)
public class NivelCuenta {
    @Id
    private Integer nivel;

    @Column(nullable = false)
    private Integer digitos;

    @Column(nullable = false, length = 2)
    private String separador; // 'SI' o 'NO'

    @Column(nullable = false, length = 5)
    private String caracter; // '.'

    public NivelCuenta() {
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getDigitos() {
        return digitos;
    }

    public void setDigitos(Integer digitos) {
        this.digitos = digitos;
    }

    public String getSeparador() {
        return separador;
    }

    public void setSeparador(String separador) {
        this.separador = separador;
    }

    public String getCaracter() {
        return caracter;
    }

    public void setCaracter(String caracter) {
        this.caracter = caracter;
    }
}
