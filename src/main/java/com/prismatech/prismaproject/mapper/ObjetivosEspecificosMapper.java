package com.prismatech.prismaproject.mapper;

import com.prismatech.prismaproject.dto.ObjetivosEspecificosDto;
import com.prismatech.prismaproject.model.ObjetivosEspecificos;

public class ObjetivosEspecificosMapper {
    public static ObjetivosEspecificosDto toDto(ObjetivosEspecificos o) {
        if (o == null) return null;
        ObjetivosEspecificosDto d = new ObjetivosEspecificosDto();
        d.setIdObjesp(o.getIdObjesp());
        if (o.getProyecto() != null) d.setIdProy(o.getProyecto().getIdProy());
        d.setObjetivoObjesp(o.getObjetivoObjesp());
        d.setEjesObjesp(o.getEjesObjesp());
        return d;
    }

    public static ObjetivosEspecificos toEntity(ObjetivosEspecificosDto d) {
        if (d == null) return null;
        ObjetivosEspecificos o = new ObjetivosEspecificos();
        o.setIdObjesp(d.getIdObjesp());
        o.setObjetivoObjesp(d.getObjetivoObjesp());
        o.setEjesObjesp(d.getEjesObjesp());
        return o;
    }
}
