package org.latacunga.resource;

import org.latacunga.dto.commons.DatosReporte;
import org.latacunga.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class ReporteResource {

    @Autowired
    private ReporteService reporteService;

    @PostMapping("/reporte")
    public void reporte(@RequestBody DatosReporte data, HttpServletResponse response) {
        try {
            streamReport(response, reporteService.generarReporte(data), data.getNombreArchivo());
        } catch (Exception e) {
            Logger.getLogger(ReporteResource.class.getName()).log(Level.SEVERE, null, e);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void streamReport(HttpServletResponse response, byte[] data, String nombreArchivo) throws IOException {
        if (data == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment; filename=" + nombreArchivo);
        response.setContentLength(data.length);
        response.getOutputStream().write(data);
        response.getOutputStream().flush();
    }
}
