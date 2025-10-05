package com.prismatech.prismaproject.mapper;

import com.prismatech.prismaproject.dto.SolicitudDesembolsoDto;
import com.prismatech.prismaproject.model.Presupuesto;
import com.prismatech.prismaproject.model.SolicitudDesembolso;

public class SolicitudDesembolsoMapper {
    public static SolicitudDesembolsoDto toDto(SolicitudDesembolso solicitud) {
        if (solicitud == null) return null;
        SolicitudDesembolsoDto dto = new SolicitudDesembolsoDto();
        dto.setIdDescm(solicitud.getIdDescm());
        if (solicitud.getPresupuesto() != null) dto.setIdPres(solicitud.getPresupuesto().getIdPres());
        dto.setDocumentoDescm(solicitud.getDocumentoDescm());
        dto.setEstadoDescm(solicitud.getEstadoDescm());
        return dto;
    }

    public static SolicitudDesembolso toEntity(SolicitudDesembolsoDto dto) {
        if (dto == null) return null;
        SolicitudDesembolso entity = new SolicitudDesembolso();
        entity.setIdDescm(dto.getIdDescm());
        entity.setDocumentoDescm(dto.getDocumentoDescm());
        entity.setEstadoDescm(dto.getEstadoDescm());
        if (dto.getIdPres() != null) {
            Presupuesto presupuesto = new Presupuesto();
            presupuesto.setIdPres(dto.getIdPres());
            entity.setPresupuesto(presupuesto);
        }
        return entity;
    }
}
