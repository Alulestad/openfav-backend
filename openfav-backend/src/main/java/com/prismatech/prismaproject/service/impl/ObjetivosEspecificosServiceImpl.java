package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.dto.ObjetivosEspecificosDto;
import com.prismatech.prismaproject.mapper.ObjetivosEspecificosMapper;
import com.prismatech.prismaproject.model.ObjetivosEspecificos;
import com.prismatech.prismaproject.model.Proyecto;
import com.prismatech.prismaproject.repository.ObjetivosEspecificosRepository;
import com.prismatech.prismaproject.repository.ProyectoRepository;
import com.prismatech.prismaproject.service.ObjetivosEspecificosService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObjetivosEspecificosServiceImpl implements ObjetivosEspecificosService {

    private final ObjetivosEspecificosRepository repository;
    private final ProyectoRepository proyectoRepository;

    public ObjetivosEspecificosServiceImpl(ObjetivosEspecificosRepository repository,
                                           ProyectoRepository proyectoRepository) {
        this.repository = repository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public ObjetivosEspecificosDto create(ObjetivosEspecificosDto dto) {
        ObjetivosEspecificos entity = toEntityWithProyecto(dto);
        ObjetivosEspecificos saved = repository.save(entity);
        return ObjetivosEspecificosMapper.toDto(saved);
    }

    @Override
    public ObjetivosEspecificosDto update(Integer id, ObjetivosEspecificosDto dto) {
        ObjetivosEspecificos existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objetivo específico no encontrado"));
        ObjetivosEspecificos entity = toEntityWithProyecto(dto);
        entity.setIdObjesp(existing.getIdObjesp());
        ObjetivosEspecificos saved = repository.save(entity);
        return ObjetivosEspecificosMapper.toDto(saved);
    }

    @Override
    public ObjetivosEspecificosDto getById(Integer id) {
        return repository.findById(id).map(ObjetivosEspecificosMapper::toDto).orElse(null);
    }

    @Override
    public List<ObjetivosEspecificosDto> getAll() {
        return repository.findAll().stream().map(ObjetivosEspecificosMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ObjetivosEspecificosDto> getByProyecto(Integer idProy) {
        return repository.findByProyecto_IdProy(idProy).stream()
                .map(ObjetivosEspecificosMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private ObjetivosEspecificos toEntityWithProyecto(ObjetivosEspecificosDto dto) {
        ObjetivosEspecificos entity = ObjetivosEspecificosMapper.toEntity(dto);
        if (dto.getIdProy() != null) {
            Proyecto proyecto = proyectoRepository.findById(dto.getIdProy())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proyecto no encontrado"));
            entity.setProyecto(proyecto);
        } else {
            entity.setProyecto(null);
        }
        return entity;
    }
}
