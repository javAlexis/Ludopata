package org.latacunga.service;

import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleCsvExporterConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.latacunga.conf.AppProps;
import org.latacunga.dto.commons.DatosReporte;
import org.latacunga.util.ReporteFormato;
import org.latacunga.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ReporteService {

    @Autowired
    private AppProps appProps;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private void verificarYCompilar(String nombreReporte) {
        try {
            String rutaJrxml = appProps.getReportes() + nombreReporte + ".jrxml";
            File fileJrxml = ResourceUtils.getFile(rutaJrxml);
            File fileJasper = new File(fileJrxml.getParentFile(), fileJrxml.getName().replace(".jrxml", ".jasper"));
            if (fileJrxml.exists() && !fileJasper.exists()) {
                System.out.println("Compilando reporte al vuelo: " + fileJrxml.getAbsolutePath() + " -> " + fileJasper.getAbsolutePath());
                net.sf.jasperreports.engine.JasperCompileManager.compileReportToFile(fileJrxml.getAbsolutePath(), fileJasper.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("No se pudo compilar el reporte al vuelo: " + e.getMessage());
        }
    }

    public byte[] generarReporte(DatosReporte datosReporte) {
        try {
            Map<String, Object> par = datosReporte.getParametros();
            if (par == null) {
                par = new HashMap<>();
            }
            if (appProps.getImagenes() != null) {
                par.putAll(Utils.getUrlsImagenes(appProps.getImagenes()));
            }
            par.put("SUBREPORT_DIR", appProps.getReportes());

            System.out.println(par.get("catalogo"));

            verificarYCompilar(datosReporte.getNombreReporte());
            if (Utils.isNotEmpty(datosReporte.getReporteAgregados())) {
                for (DatosReporte dr : datosReporte.getReporteAgregados()) {
                    verificarYCompilar(dr.getNombreReporte());
                }
            }

            String ruta = Utils.armarRutaJasper(appProps.getReportes(), datosReporte.getNombreReporte());
            if (Boolean.TRUE.equals(datosReporte.getDataSource())) {
                return generarJasperXModelo(ruta, datosReporte.getDataList(), par, datosReporte);
            }
            return generarJasperXConexion(ruta, par, datosReporte);
        } catch (Exception e) {
            Logger.getLogger(ReporteService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public byte[] generarReportePDF(Map<String, Object> parametrosReporte, String nombreReporte, String nombreArchivo) {
        DatosReporte datosReporte = new DatosReporte();
        datosReporte.setDataSource(Boolean.FALSE);
        datosReporte.setFormato(ReporteFormato.PDF.getCodigo());
        datosReporte.setNombreReporte(nombreReporte);
        datosReporte.setNombreArchivo(nombreArchivo + ".pdf");
        datosReporte.setParametros(parametrosReporte);
        return generarReporte(datosReporte);
    }

    private byte[] generarJasperXConexion(String resourceLocation, Map<String, Object> parameters, DatosReporte datosReporte) {
        try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
            File file = ResourceUtils.getFile(resourceLocation);
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parameters, con);
            if (Utils.isNotEmpty(datosReporte.getReporteAgregados())) {
                for (DatosReporte dr : datosReporte.getReporteAgregados()) {
                    agregarSubReporte(jasperPrint, dr, con);
                }
            }
            return bytesReporte(datosReporte, jasperPrint);
        } catch (Exception e) {
            Logger.getLogger(ReporteService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private void agregarSubReporte(JasperPrint jasperPrint, DatosReporte dr, Connection con) {
        try {
            Map<String, Object> par = dr.getParametros();
            if (par == null) {
                par = new HashMap<>();
            }
            par.putAll(Utils.getUrlsImagenes(appProps.getImagenes()));
            par.put("SUBREPORT_DIR", appProps.getReportes());
            String rutaJasper = Utils.armarRutaJasper(appProps.getReportes(), dr.getNombreReporte());
            File filea = ResourceUtils.getFile(rutaJasper);
            JasperPrint jpa = JasperFillManager.fillReport(filea.getAbsolutePath(), par, con);
            if (jpa != null && jpa.getPages() != null) {
                for (JRPrintPage page : jpa.getPages()) {
                    if (!page.getElements().isEmpty()) {
                        jasperPrint.addPage(page);
                    }
                }
            }
        } catch (Exception e) {
            Logger.getLogger(ReporteService.class.getName()).log(Level.SEVERE, "Error al generar reporte adicional", e);
        }
    }

    private byte[] generarJasperXModelo(String resourceLocation, List model, Map<String, Object> parameters, DatosReporte datosReporte) {
        try (Connection con = jdbcTemplate.getDataSource().getConnection()) {
            parameters.put("REPORT_CONNECTION", con);
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(model);
            File file = ResourceUtils.getFile(resourceLocation);
            JasperPrint jasperPrint = JasperFillManager.fillReport(file.getAbsolutePath(), parameters, jrBeanCollectionDataSource);
            return bytesReporte(datosReporte, jasperPrint);
        } catch (Exception e) {
            Logger.getLogger(ReporteService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private byte[] bytesReporte(DatosReporte datosReporte, JasperPrint jasperPrint) {
        try {
            switch (getByCodigo(datosReporte.getFormato())) {
                case PDF:
                    return JasperExportManager.exportReportToPdf(jasperPrint);
                case CSV:
                    return exportarCsv(jasperPrint);
                case EXCEL:
                    return exportarExcel(datosReporte, jasperPrint);
                case WORD:
                    return exportarWord(jasperPrint);
                default:
                    return null;
            }
        } catch (Exception e) {
            Logger.getLogger(ReporteService.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    private byte[] exportarCsv(JasperPrint jasperPrint) throws Exception {
        JRCsvExporter exp = new JRCsvExporter();
        exp.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(output, StandardCharsets.ISO_8859_1);
        exp.setExporterOutput(new SimpleWriterExporterOutput(writer));
        exp.setConfiguration(new SimpleCsvExporterConfiguration());
        exp.exportReport();
        return output.toByteArray();
    }

    private byte[] exportarExcel(DatosReporte datosReporte, JasperPrint jasperPrint) throws Exception {
        Map<String, Object> par = datosReporte.getParametros();
        if (par == null) {
            par = new HashMap<>();
        }
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet((Boolean) par.get("IS_ONE_PAGE_PER_SHEET"));
        configuration.setIgnoreGraphics(false);
        configuration.setDetectCellType(true);
        configuration.setShowGridLines((Boolean) (par.get("SHOW_GRID_LINES") == null ? Boolean.TRUE : par.get("SHOW_GRID_LINES")));
        configuration.setRemoveEmptySpaceBetweenColumns((Boolean) (par.get("REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS") == null ? Boolean.FALSE : par.get("REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS")));
        configuration.setRemoveEmptySpaceBetweenRows((Boolean) (par.get("REMOVE_EMPTY_SPACE_BETWEEN_ROWS") == null ? Boolean.FALSE : par.get("REMOVE_EMPTY_SPACE_BETWEEN_ROWS")));

        File outputFile = File.createTempFile(datosReporte.getNombreArchivo(), ".xlsx");
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             OutputStream fileOutputStream = new FileOutputStream(outputFile)) {
            Exporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            byteArrayOutputStream.writeTo(fileOutputStream);
        }
        byte[] arr = null;
        if (outputFile.exists()) {
            arr = Files.readAllBytes(outputFile.toPath());
            outputFile.delete();
        }
        return arr;
    }

    private byte[] exportarWord(JasperPrint jasperPrint) throws Exception {
        Exporter exporter = new JRDocxExporter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.exportReport();
        return out.toByteArray();
    }

    public ReporteFormato getByCodigo(String codigo) {
        for (ReporteFormato value : ReporteFormato.values()) {
            if (value.getCodigo().equals(codigo)) {
                return value;
            }
        }
        return null;
    }
}
