package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.dto.ActividadDto;
import com.prismatech.prismaproject.mapper.ActividadMapper;
import com.prismatech.prismaproject.model.Actividad;
import com.prismatech.prismaproject.model.ObjetivosEspecificos;
import com.prismatech.prismaproject.repository.ActividadRepository;
import com.prismatech.prismaproject.repository.ObjetivosEspecificosRepository;
import com.prismatech.prismaproject.service.ActividadService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActividadServiceImpl implements ActividadService {
    private final ActividadRepository repo;
    private final ObjetivosEspecificosRepository objRepo;

    public ActividadServiceImpl(ActividadRepository repo, ObjetivosEspecificosRepository objRepo) {
        this.repo = repo;
        this.objRepo = objRepo;
    }

    @Override
    public ActividadDto create(ActividadDto dto) {
        Actividad a = ActividadMapper.toEntity(dto);
        if (dto.getIdObjesp() != null) {
            ObjetivosEspecificos o = objRepo.findById(dto.getIdObjesp()).orElse(null);
            a.setObjetivoEspecifico(o);
        }
        Actividad saved = repo.save(a);
        return ActividadMapper.toDto(saved);
    }

    @Override
    public ActividadDto update(Integer id, ActividadDto dto) {
        Actividad existing = repo.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        Actividad toSave = ActividadMapper.toEntity(dto);
        toSave.setIdActi(existing.getIdActi());
        if (dto.getIdObjesp() != null) {
            ObjetivosEspecificos o = objRepo.findById(dto.getIdObjesp()).orElse(null);
            toSave.setObjetivoEspecifico(o);
        }
        Actividad saved = repo.save(toSave);
        return ActividadMapper.toDto(saved);
    }

    @Override
    public ActividadDto getById(Integer id) {
        return repo.findById(id).map(ActividadMapper::toDto).orElse(null);
    }

    @Override
    public List<ActividadDto> getAll() {
        return repo.findAll().stream().map(ActividadMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
