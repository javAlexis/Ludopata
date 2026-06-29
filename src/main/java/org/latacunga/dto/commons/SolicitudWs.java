package org.latacunga.dto.commons;

public class SolicitudWs {
    private Long id;
    private String estado;
    private String cedula;
    private String nombre;


    public SolicitudWs() {}
    public SolicitudWs(Long id) {}
    public SolicitudWs(Long id, String estado, String cedula) {}

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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

