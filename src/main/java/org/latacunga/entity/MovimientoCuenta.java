package org.latacunga.entity;

import org.latacunga.util.EsquemaConfig;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "movimiento_cuenta", schema = EsquemaConfig.capacitacion)
public class MovimientoCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuenta", nullable = false)
    private Cuenta cuenta;

    private BigDecimal debe;
    private BigDecimal haber;

    public MovimientoCuenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
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
