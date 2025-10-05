package com.prismatech.prismaproject.mapper;

import com.prismatech.prismaproject.dto.ActividadDto;
import com.prismatech.prismaproject.model.Actividad;

public class ActividadMapper {
    public static ActividadDto toDto(Actividad a) {
        if (a == null) return null;
        ActividadDto d = new ActividadDto();
        d.setIdActi(a.getIdActi());
        if (a.getObjetivoEspecifico() != null) d.setIdObjesp(a.getObjetivoEspecifico().getIdObjesp());
        d.setNombreActi(a.getNombreActi());
        d.setDescripcionActi(a.getDescripcionActi());
        d.setResultadoEsperadoActi(a.getResultadoEsperadoActi());
        d.setResultadoObtenido(a.getResultadoObtenido());
        d.setCategoria(a.getCategoria());
        return d;
    }

    public static Actividad toEntity(ActividadDto d) {
        if (d == null) return null;
        Actividad a = new Actividad();
        a.setIdActi(d.getIdActi());
        a.setNombreActi(d.getNombreActi());
        a.setDescripcionActi(d.getDescripcionActi());
        a.setResultadoEsperadoActi(d.getResultadoEsperadoActi());
        a.setResultadoObtenido(d.getResultadoObtenido());
        a.setCategoria(d.getCategoria());
        return a;
    }
}
