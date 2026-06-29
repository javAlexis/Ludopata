package org.latacunga.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    private Utils() {
    }

    public static boolean isNotEmpty(List list) {
        return list != null && !list.isEmpty();
    }

    public static String armarRutaJasper(String rutaReporte, String nombre) {
        return rutaReporte + nombre + ".jasper";
    }

    public static Map<String, Object> getUrlsImagenes(String rutaImagenes) {
        Map<String, Object> map = new HashMap<>();
        map.put("HEADER_URL", rutaImagenes + "cabeceraIB.png");
        map.put("LOGO_HEADER", rutaImagenes + "logo_header.png");
        map.put("WATERMARK_URL", rutaImagenes + "marca_agua.png");
        map.put("BACKGROUND", rutaImagenes + "marca_agua.png");
        map.put("FOOTER_URL", rutaImagenes + "pie_pagina.png");
        map.put("IMG_REPORT", rutaImagenes + "escudo_latacunga.png");
        return map;
    }
}
