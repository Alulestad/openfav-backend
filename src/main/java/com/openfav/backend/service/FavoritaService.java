package com.openfav.backend.service;

import com.openfav.backend.domain.model.Ong;
import com.openfav.backend.domain.model.Proyecto;
import com.openfav.backend.domain.repository.OngRepository;
import com.openfav.backend.domain.repository.ProyectoRepository;
import com.openfav.backend.exception.NotFoundException;
import com.openfav.backend.service.dto.OngProfileDto;
import com.openfav.backend.service.dto.ProjectDetailDto;
import com.openfav.backend.service.dto.ProjectSummaryDto;
import com.openfav.backend.service.mapper.OngMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoritaService {

    private final OngRepository ongRepository;
    private final ProyectoRepository proyectoRepository;

    public FavoritaService(OngRepository ongRepository, ProyectoRepository proyectoRepository) {
        this.ongRepository = ongRepository;
        this.proyectoRepository = proyectoRepository;
    }

    public List<OngProfileDto> listOngs() {
        return ongRepository.findAll().stream()
                .map(OngMapper::toProfileDto)
                .toList();
    }

    public List<ProjectSummaryDto> listProjectsByOng(Long ongId) {
        List<Proyecto> proyectos = proyectoRepository.findByOngsId(ongId);
        return proyectos.stream()
                .map(OngMapper::toProjectSummaryDto)
                .collect(Collectors.toList());
    }

    public ProjectDetailDto getProjectDetail(Long projectId) {
        Proyecto proyecto = proyectoRepository.findDetailedById(projectId)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
        return OngMapper.toProjectDetailDto(proyecto);
    }
}
