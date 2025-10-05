package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.dto.ProyectoDto;

import java.util.List;

public interface ProyectoService {
    ProyectoDto create(ProyectoDto dto);
    ProyectoDto update(Integer id, ProyectoDto dto);
    ProyectoDto getById(Integer id);
    List<ProyectoDto> getAll();
    void delete(Integer id);
}
