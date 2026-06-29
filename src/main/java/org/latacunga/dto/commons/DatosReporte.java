package org.latacunga.dto.commons;

import java.util.List;
import java.util.Map;

public class DatosReporte {

    private String nombreReporte;
    private String nombreArchivo;
    private String formato;
    private Boolean dataSource; // True si se llena con un modelo de datos (lista Java) : False si es por conexion SQL directa
    private Map<String, Object> parametros;
    private List dataList;
    private List<DatosReporte> reporteAgregados; // sub-reportes que se anexan como paginas adicionales

    public DatosReporte() {
    }

    public String getNombreReporte() {
        return nombreReporte;
    }

    public void setNombreReporte(String nombreReporte) {
        this.nombreReporte = nombreReporte;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public Boolean getDataSource() {
        return dataSource;
    }

    public void setDataSource(Boolean dataSource) {
        this.dataSource = dataSource;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public List<DatosReporte> getReporteAgregados() {
        return reporteAgregados;
    }

    public void setReporteAgregados(List<DatosReporte> reporteAgregados) {
        this.reporteAgregados = reporteAgregados;
    }

    @Override
    public String toString() {
        return "DatosReporte{" +
                "nombreReporte='" + nombreReporte + '\'' +
                ", nombreArchivo='" + nombreArchivo + '\'' +
                ", formato='" + formato + '\'' +
                ", dataSource=" + dataSource +
                ", parametros=" + parametros +
                '}';
    }
}
