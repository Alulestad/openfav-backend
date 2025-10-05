package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.dto.ObjetivosEspecificosDto;

import java.util.List;

public interface ObjetivosEspecificosService {
    ObjetivosEspecificosDto create(ObjetivosEspecificosDto dto);
    ObjetivosEspecificosDto update(Integer id, ObjetivosEspecificosDto dto);
    ObjetivosEspecificosDto getById(Integer id);
    List<ObjetivosEspecificosDto> getAll();
    List<ObjetivosEspecificosDto> getByProyecto(Integer idProy);
    void delete(Integer id);
}
