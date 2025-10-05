package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.dto.SolicitudDesembolsoDto;
import com.prismatech.prismaproject.mapper.SolicitudDesembolsoMapper;
import com.prismatech.prismaproject.model.Presupuesto;
import com.prismatech.prismaproject.model.SolicitudDesembolso;
import com.prismatech.prismaproject.repository.PresupuestoRepository;
import com.prismatech.prismaproject.repository.SolicitudDesembolsoRepository;
import com.prismatech.prismaproject.service.SolicitudDesembolsoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SolicitudDesembolsoServiceImpl implements SolicitudDesembolsoService {

    private final SolicitudDesembolsoRepository repository;
    private final PresupuestoRepository presupuestoRepository;

    public SolicitudDesembolsoServiceImpl(SolicitudDesembolsoRepository repository,
                                          PresupuestoRepository presupuestoRepository) {
        this.repository = repository;
        this.presupuestoRepository = presupuestoRepository;
    }

    @Override
    public SolicitudDesembolsoDto create(SolicitudDesembolsoDto dto) {
        SolicitudDesembolso entity = toEntityWithPresupuesto(dto);
        if (entity.getEstadoDescm() == null || entity.getEstadoDescm().isBlank()) {
            entity.setEstadoDescm("Enviado");
        }
        SolicitudDesembolso saved = repository.save(entity);
        return SolicitudDesembolsoMapper.toDto(saved);
    }

    @Override
    public SolicitudDesembolsoDto update(Integer id, SolicitudDesembolsoDto dto) {
        SolicitudDesembolso existing = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Solicitud no encontrada"));
        SolicitudDesembolso entity = toEntityWithPresupuesto(dto);
        entity.setIdDescm(existing.getIdDescm());
        if (entity.getEstadoDescm() == null) {
            entity.setEstadoDescm(existing.getEstadoDescm());
        }
        SolicitudDesembolso saved = repository.save(entity);
        return SolicitudDesembolsoMapper.toDto(saved);
    }

    @Override
    public SolicitudDesembolsoDto getById(Integer id) {
        return repository.findById(id).map(SolicitudDesembolsoMapper::toDto).orElse(null);
    }

    @Override
    public List<SolicitudDesembolsoDto> getAll() {
        return repository.findAll().stream()
                .map(SolicitudDesembolsoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudDesembolsoDto> getByPresupuesto(Integer idPres) {
        return repository.findByPresupuesto_IdPres(idPres).stream()
                .map(SolicitudDesembolsoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SolicitudDesembolsoDto> getByProyecto(Integer idProy) {
        return repository.findByPresupuesto_Proyecto_IdProy(idProy).stream()
                .map(SolicitudDesembolsoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    private SolicitudDesembolso toEntityWithPresupuesto(SolicitudDesembolsoDto dto) {
        SolicitudDesembolso entity = SolicitudDesembolsoMapper.toEntity(dto);
        if (dto.getIdPres() != null) {
            Presupuesto presupuesto = presupuestoRepository.findById(dto.getIdPres())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Presupuesto no encontrado"));
            entity.setPresupuesto(presupuesto);
        } else {
            entity.setPresupuesto(null);
        }
        return entity;
    }
}
