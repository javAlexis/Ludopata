package org.latacunga.dto.commons;

import java.io.Serializable;

public class RespuestaWs implements Serializable {

    private Long id;
    private Boolean estado;
    private String data;
    private String mensaje;

    public RespuestaWs() {
    }

    public RespuestaWs(Boolean estado, String data) {
        this.estado = estado;
        this.data = data;
    }

    public RespuestaWs(Boolean estado, String data, String mensaje) {
        this.estado = estado;
        this.data = data;
        this.mensaje = mensaje;
    }

    public RespuestaWs(Boolean estado, String data, String mensaje, Long id) {
        this.estado = estado;
        this.data = data;
        this.mensaje = mensaje;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "RespuestaWs{" +
                "id=" + id +
                ", estado=" + estado +
                ", data='" + data + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
