package org.latacunga.service;

import org.latacunga.dto.CuentaDto;
import org.latacunga.dto.commons.RespuestaWs;
import org.latacunga.entity.Cuenta;
import org.latacunga.entity.NivelCuenta;
import org.latacunga.entity.MovimientoCuenta;
import org.latacunga.mapper.CuentaMapper;
import org.latacunga.repository.CuentaRepository;
import org.latacunga.repository.NivelCuentaRepository;
import org.latacunga.repository.MovimientoCuentaRepository;
import org.latacunga.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private NivelCuentaRepository nivelCuentaRepository;
    @Autowired
    private MovimientoCuentaRepository movimientoCuentaRepository;
    @Autowired
    private CuentaMapper cuentaMapper;

    public List<Cuenta> consultarCuentas() {
        try {
            List<Cuenta> cuentas = cuentaRepository.findAllByEstadoOrderByCodigoAsc(Constantes.estadoActivo);
            // Calcular saldos acumulados de movimientos contables para cada cuenta
            for (Cuenta c : cuentas) {
                List<MovimientoCuenta> movimientos = movimientoCuentaRepository.findAllByCuentaId(c.getId());
                BigDecimal totalDebe = BigDecimal.ZERO;
                BigDecimal totalHaber = BigDecimal.ZERO;
                for (MovimientoCuenta m : movimientos) {
                    if (m.getDebe() != null) {
                        totalDebe = totalDebe.add(m.getDebe());
                    }
                    if (m.getHaber() != null) {
                        totalHaber = totalHaber.add(m.getHaber());
                    }
                }
                c.setDebito(totalDebe);
                c.setCredito(totalHaber);
            }
            return cuentas;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public String generarCodigo(Integer nivel, String grupo, Integer secuencia) {
        if (nivel == null || secuencia == null) {
            return "";
        }
        NivelCuenta nivelConfig = nivelCuentaRepository.findById(nivel).orElse(null);
        if (nivelConfig == null) {
            return grupo != null ? grupo + secuencia : String.valueOf(secuencia);
        }

        // Formatear la secuencia con ceros a la izquierda según los dígitos especificados
        String seqStr = String.format("%0" + nivelConfig.getDigitos() + "d", secuencia);

        if (nivel == 1) {
            return seqStr;
        }

        // Si es nivel > 1
        String prefix = (grupo == null) ? "" : grupo;
        
        // Verificar si este nivel lleva separador antes
        if ("SI".equalsIgnoreCase(nivelConfig.getSeparador())) {
            String sep = (nivelConfig.getCaracter() != null) ? nivelConfig.getCaracter() : ".";
            if (!prefix.isEmpty() && !prefix.endsWith(sep)) {
                prefix = prefix + sep;
            }
        }

        return prefix + seqStr;
    }

    public RespuestaWs guardar(CuentaDto cuentaDto) {
        try {
            // Generar código antes de guardar
            String codigoCalculado = generarCodigo(cuentaDto.getNivel(), cuentaDto.getGrupo() != null ? String.valueOf(cuentaDto.getGrupo()) : null, cuentaDto.getSecuencia());
            if (codigoCalculado != null && !codigoCalculado.isEmpty()) {
                cuentaDto.setCodigo(codigoCalculado);
            }

            // Validar que el código no esté duplicado
            Cuenta cuentaExistente = cuentaRepository.findByCodigoAndEstado(cuentaDto.getCodigo(), Constantes.estadoActivo);
            if (cuentaExistente != null) {
                if (cuentaDto.getId() == null || !cuentaExistente.getId().equals(cuentaDto.getId())) {
                    return new RespuestaWs(Boolean.FALSE, null, "El código contable '" + cuentaDto.getCodigo() + "' ya existe.");
                }
            }

            Cuenta cuenta = cuentaMapper.toEntity(cuentaDto);
            
            // Si el nivel es 4 o más, por defecto el tipo es 'M' (Movimiento), de lo contrario 'A' (Agrupación)
            if (cuenta.getNivel() != null && cuenta.getNivel() >= 4) {
                cuenta.setTipo("M");
            } else {
                cuenta.setTipo("A");
            }

            if (cuenta.getId() == null) {
                cuenta.setEstado(Constantes.estadoActivo);
                cuenta.setFechaCreacion(new Date());
                cuenta.setUsuarioCreacion("ADMIN");
                cuenta.setDebito(BigDecimal.ZERO);
                cuenta.setCredito(BigDecimal.ZERO);
            } else {
                Cuenta anterior = cuentaRepository.findById(cuenta.getId()).orElse(null);
                if (anterior != null) {
                    cuenta.setFechaCreacion(anterior.getFechaCreacion());
                    cuenta.setUsuarioCreacion(anterior.getUsuarioCreacion());
                    cuenta.setDebito(anterior.getDebito());
                    cuenta.setCredito(anterior.getCredito());
                }
                cuenta.setFechaEdicion(new Date());
                cuenta.setUsuarioEdicion("ADMIN");
            }
            
            cuentaRepository.save(cuenta);
            return new RespuestaWs(Boolean.TRUE, "CUENTA GUARDADA", "CUENTA GUARDADA EXITOSAMENTE");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespuestaWs(Boolean.FALSE, e.getMessage(), "Error al guardar la cuenta");
        }
    }

    public RespuestaWs eliminar(Long id) {
        try {
            Cuenta cuenta = cuentaRepository.findById(id).orElse(null);
            if (cuenta != null) {
                cuenta.setEstado(Constantes.estadoInactivo);
                cuenta.setFechaEdicion(new Date());
                cuenta.setUsuarioEdicion("ADMIN");
                cuentaRepository.save(cuenta);
                return new RespuestaWs(Boolean.TRUE, "CUENTA ELIMINADA", "CUENTA ELIMINADA EXITOSAMENTE");
            }
            return new RespuestaWs(Boolean.FALSE, null, "La cuenta no existe");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespuestaWs(Boolean.FALSE, e.getMessage(), "Error al eliminar la cuenta");
        }
    }
}
