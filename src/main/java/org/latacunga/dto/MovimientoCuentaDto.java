package org.latacunga.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class MovimientoCuentaDto implements Serializable {
    private Long id;
    private Long idCuenta;
    private BigDecimal debe;
    private BigDecimal haber;

    public MovimientoCuentaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public BigDecimal getDebe() {
        return debe;
    }

    public void setDebe(BigDecimal debe) {
        this.debe = debe;
    }

    public BigDecimal getHaber() {
        return haber;
    }

    public void setHaber(BigDecimal haber) {
        this.haber = haber;
    }
}
