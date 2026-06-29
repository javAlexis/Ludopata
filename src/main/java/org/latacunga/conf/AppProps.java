package org.latacunga.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppProps {

    @Value("${app.reportes}")
    private String reportes;
    @Value("${app.imagenes}")
    private String imagenes;

    public String getReportes() {
        return reportes;
    }

    public void setReportes(String reportes) {
        this.reportes = reportes;
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }
}
