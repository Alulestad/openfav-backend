package com.openfav.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.ObjetivoEspecificoRequest;
import com.openfav.model.Actividad;
import com.openfav.model.ObjetivoEspecifico;
import com.openfav.model.Proyecto;
import com.openfav.repository.ActividadFechaRepository;
import com.openfav.repository.ActividadRepository;
import com.openfav.repository.KpiRepository;
import com.openfav.repository.ObjetivoEspecificoRepository;
import com.openfav.repository.ProyectoRepository;

@Service
public class ObjetivoEspecificoService {

    private final ObjetivoEspecificoRepository objetivoRepository;
    private final ProyectoRepository proyectoRepository;
    private final ActividadRepository actividadRepository;
    private final ActividadFechaRepository actividadFechaRepository;
    private final KpiRepository kpiRepository;

    public ObjetivoEspecificoService(ObjetivoEspecificoRepository objetivoRepository,
            ProyectoRepository proyectoRepository,
            ActividadRepository actividadRepository,
            ActividadFechaRepository actividadFechaRepository,
            KpiRepository kpiRepository) {
        this.objetivoRepository = objetivoRepository;
        this.proyectoRepository = proyectoRepository;
        this.actividadRepository = actividadRepository;
        this.actividadFechaRepository = actividadFechaRepository;
        this.kpiRepository = kpiRepository;
    }

    @Transactional
    public ObjetivoEspecifico crear(Integer proyectoId, ObjetivoEspecificoRequest request) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        ObjetivoEspecifico objetivo = new ObjetivoEspecifico();
        objetivo.setProyecto(proyecto);
        objetivo.setObjetivo(request.getObjetivo());
        objetivo.setEjes(request.getEjes());
        return objetivoRepository.save(objetivo);
    }

    @Transactional
    public ObjetivoEspecifico actualizar(Integer objetivoId, ObjetivoEspecificoRequest request) {
        ObjetivoEspecifico objetivo = objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new IllegalArgumentException("Objetivo específico no encontrado"));
        objetivo.setObjetivo(request.getObjetivo());
        objetivo.setEjes(request.getEjes());
        return objetivoRepository.save(objetivo);
    }

    @Transactional(readOnly = true)
    public ObjetivoEspecifico obtener(Integer objetivoId) {
        return objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new IllegalArgumentException("Objetivo específico no encontrado"));
    }

    @Transactional
    public void eliminar(Integer objetivoId) {
        ObjetivoEspecifico objetivo = obtener(objetivoId);
        List<Actividad> actividades = actividadRepository.findByObjetivoEspecificoId(objetivo.getId());
        for (Actividad actividad : actividades) {
            actividadFechaRepository.deleteAll(actividadFechaRepository.findByActividadId(actividad.getId()));
            kpiRepository.deleteAll(kpiRepository.findByActividadId(actividad.getId()));
        }
        actividadRepository.deleteAll(actividades);
        objetivoRepository.delete(objetivo);
    }
}
