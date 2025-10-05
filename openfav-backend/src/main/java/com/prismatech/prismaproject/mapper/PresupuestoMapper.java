package com.prismatech.prismaproject.mapper;

import com.prismatech.prismaproject.dto.PresupuestoDto;
import com.prismatech.prismaproject.model.Presupuesto;
import com.prismatech.prismaproject.model.Proyecto;

public class PresupuestoMapper {
    public static PresupuestoDto toDto(Presupuesto p) {
        if (p == null) return null;
        PresupuestoDto dto = new PresupuestoDto();
        dto.setIdPres(p.getIdPres());
        if (p.getProyecto() != null) dto.setIdProy(p.getProyecto().getIdProy());
        dto.setCategoriaPres(p.getCategoriaPres());
        dto.setCantidadPres(p.getCantidadPres());
        dto.setUnidadesPres(p.getUnidadesPres());
        dto.setValorPres(p.getValorPres());
        return dto;
    }

    public static Presupuesto toEntity(PresupuestoDto dto) {
        if (dto == null) return null;
        Presupuesto entity = new Presupuesto();
        entity.setIdPres(dto.getIdPres());
        entity.setCategoriaPres(dto.getCategoriaPres());
        entity.setCantidadPres(dto.getCantidadPres());
        entity.setUnidadesPres(dto.getUnidadesPres());
        entity.setValorPres(dto.getValorPres());
        if (dto.getIdProy() != null) {
            Proyecto proyecto = new Proyecto();
            proyecto.setIdProy(dto.getIdProy());
            entity.setProyecto(proyecto);
        }
        return entity;
    }
}
