package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.dto.ProyectoDto;
import com.prismatech.prismaproject.mapper.ProyectoMapper;
import com.prismatech.prismaproject.model.Proyecto;
import com.prismatech.prismaproject.repository.ProyectoRepository;
import com.prismatech.prismaproject.service.ProyectoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProyectoServiceImpl implements ProyectoService {
    private final ProyectoRepository repo;

    public ProyectoServiceImpl(ProyectoRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProyectoDto create(ProyectoDto dto) {
        Proyecto p = ProyectoMapper.toEntity(dto);
        Proyecto saved = repo.save(p);
        return ProyectoMapper.toDto(saved);
    }

    @Override
    public ProyectoDto update(Integer id, ProyectoDto dto) {
        Proyecto existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        Proyecto updated = ProyectoMapper.toEntity(dto);
        updated.setIdProy(existing.getIdProy());
        Proyecto saved = repo.save(updated);
        return ProyectoMapper.toDto(saved);
    }

    @Override
    public ProyectoDto getById(Integer id) {
        return repo.findById(id).map(ProyectoMapper::toDto).orElse(null);
    }

    @Override
    public List<ProyectoDto> getAll() {
        return repo.findAll().stream().map(ProyectoMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
