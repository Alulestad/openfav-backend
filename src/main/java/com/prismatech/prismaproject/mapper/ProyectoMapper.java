package com.prismatech.prismaproject.mapper;

import com.prismatech.prismaproject.dto.ProyectoDto;
import com.prismatech.prismaproject.model.Proyecto;

public class ProyectoMapper {
    public static ProyectoDto toDto(Proyecto p) {
        if (p == null) return null;
        ProyectoDto d = new ProyectoDto();
        d.setIdProy(p.getIdProy());
        d.setTituloProy(p.getTituloProy());
        d.setObjetivoGeneralProy(p.getObjetivoGeneralProy());
        d.setAlcanceProy(p.getAlcanceProy());
        d.setMontoTotalProy(p.getMontoTotalProy());
        d.setPlazoTotalProy(p.getPlazoTotalProy());
        d.setFechaInicioProy(p.getFechaInicioProy());
        d.setFechaFinProy(p.getFechaFinProy());
        d.setEjesProy(p.getEjesProy());
        d.setResultadoProy(p.getResultadoProy());
        return d;
    }

    public static Proyecto toEntity(ProyectoDto d) {
        if (d == null) return null;
        Proyecto p = new Proyecto();
        p.setIdProy(d.getIdProy());
        p.setTituloProy(d.getTituloProy());
        p.setObjetivoGeneralProy(d.getObjetivoGeneralProy());
        p.setAlcanceProy(d.getAlcanceProy());
        p.setMontoTotalProy(d.getMontoTotalProy());
        p.setPlazoTotalProy(d.getPlazoTotalProy());
        p.setFechaInicioProy(d.getFechaInicioProy());
        p.setFechaFinProy(d.getFechaFinProy());
        p.setEjesProy(d.getEjesProy());
        p.setResultadoProy(d.getResultadoProy());
        return p;
    }
}
