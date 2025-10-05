package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.dto.PresupuestoDto;

import java.util.List;

public interface PresupuestoService {
    PresupuestoDto create(PresupuestoDto dto);
    PresupuestoDto update(Integer id, PresupuestoDto dto);
    PresupuestoDto getById(Integer id);
    List<PresupuestoDto> getAll();
    List<PresupuestoDto> getByProyecto(Integer idProy);
    void delete(Integer id);
}
