package org.latacunga.dto;

import java.io.Serializable;

public class NivelCuentaDto implements Serializable {
    private Integer nivel;
    private Integer digitos;
    private String separador;
    private String caracter;

    public NivelCuentaDto() {
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
