package com.openfav.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.OngProfileDto;
import com.openfav.dto.ProyectoResumenDto;
import com.openfav.model.Ong;
import com.openfav.model.Proyecto;
import com.openfav.repository.OngRepository;
import com.openfav.repository.ProyectoRepository;

@Service
public class OngService {

    private final OngRepository ongRepository;
    private final ProyectoRepository proyectoRepository;

    public OngService(OngRepository ongRepository, ProyectoRepository proyectoRepository) {
        this.ongRepository = ongRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Transactional(readOnly = true)
    public OngProfileDto getPerfil(Integer ongId) {
        Ong ong = ongRepository.findById(ongId)
                .orElseThrow(() -> new IllegalArgumentException("ONG no encontrada"));

        OngProfileDto dto = new OngProfileDto();
        dto.setId(ong.getId());
        dto.setNombre(ong.getNombre());
        dto.setRepresentanteLegal(ong.getRepresentanteLegal());
        dto.setEmail(ong.getEmail());
        dto.setCelular(ong.getCelular());
        dto.setDireccion(ong.getDireccion());
        return dto;
    }

    @Transactional(readOnly = true)
    public List<ProyectoResumenDto> listarProyectos(Integer ongId) {
        List<Proyecto> proyectos = proyectoRepository.findByOngId(ongId);
        return proyectos.stream()
                .map(this::mapResumen)
                .collect(Collectors.toList());
    }

    private ProyectoResumenDto mapResumen(Proyecto proyecto) {
        ProyectoResumenDto dto = new ProyectoResumenDto();
        dto.setId(proyecto.getId());
        dto.setTitulo(proyecto.getTitulo());
        dto.setFechaInicio(proyecto.getFechaInicio());
        dto.setFechaFin(proyecto.getFechaFin());
        String descripcion = proyecto.getObjetivoGeneral();
        if (descripcion == null || descripcion.isBlank()) {
            descripcion = proyecto.getResultado();
        }
        if (descripcion == null) {
            descripcion = "";
        }
        dto.setDescripcionCorta(descripcion.length() > 120 ? descripcion.substring(0, 120) + "..." : descripcion);
        return dto;
    }
}
