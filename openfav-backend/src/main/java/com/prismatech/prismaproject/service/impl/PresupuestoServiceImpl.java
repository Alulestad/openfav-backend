package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.dto.PresupuestoDto;
import com.prismatech.prismaproject.mapper.PresupuestoMapper;
import com.prismatech.prismaproject.model.Presupuesto;
import com.prismatech.prismaproject.model.Proyecto;
import com.prismatech.prismaproject.repository.PresupuestoRepository;
import com.prismatech.prismaproject.repository.ProyectoRepository;
import com.prismatech.prismaproject.service.PresupuestoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PresupuestoServiceImpl implements PresupuestoService {

    private final PresupuestoRepository repository;
    private final ProyectoRepository proyectoRepository;

    public PresupuestoServiceImpl(PresupuestoRepository repository, ProyectoRepository proyectoRepository) {
        this.repository = repository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public PresupuestoDto create(PresupuestoDto dto) {
        Presupuesto entity = toEntityWithProyecto(dto);
        Presupuesto saved = repository.save(entity);
        return PresupuestoMapper.toDto(saved);
    }

    @Override
    public PresupuestoDto update(Integer id, PresupuestoDto dto) {
        Presupuesto existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Presupuesto no encontrado"));
        Presupuesto entity = toEntityWithProyecto(dto);
        entity.setIdPres(existing.getIdPres());
        Presupuesto saved = repository.save(entity);
        return PresupuestoMapper.toDto(saved);
    }

    @Override
    public PresupuestoDto getById(Integer id) {
        return repository.findById(id).map(PresupuestoMapper::toDto).orElse(null);
    }

    @Override
    public List<PresupuestoDto> getAll() {
        return repository.findAll().stream().map(PresupuestoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<PresupuestoDto> getByProyecto(Integer idProy) {
        return repository.findByProyecto_IdProy(idProy).stream()
                .map(PresupuestoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private Presupuesto toEntityWithProyecto(PresupuestoDto dto) {
        Presupuesto entity = PresupuestoMapper.toEntity(dto);
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
