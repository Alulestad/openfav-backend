package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.dto.ActividadDto;

import java.util.List;

public interface ActividadService {
    ActividadDto create(ActividadDto dto);
    ActividadDto update(Integer id, ActividadDto dto);
    ActividadDto getById(Integer id);
    List<ActividadDto> getAll();
    void delete(Integer id);
}
