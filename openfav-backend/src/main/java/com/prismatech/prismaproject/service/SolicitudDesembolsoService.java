package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.dto.SolicitudDesembolsoDto;

import java.util.List;

public interface SolicitudDesembolsoService {
    SolicitudDesembolsoDto create(SolicitudDesembolsoDto dto);
    SolicitudDesembolsoDto update(Integer id, SolicitudDesembolsoDto dto);
    SolicitudDesembolsoDto getById(Integer id);
    List<SolicitudDesembolsoDto> getAll();
    List<SolicitudDesembolsoDto> getByPresupuesto(Integer idPres);
    List<SolicitudDesembolsoDto> getByProyecto(Integer idProy);
    void delete(Integer id);
}
