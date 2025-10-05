package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.Actividad;
import com.senadi.pasantes.intranet.model.ActividadFecha;
import com.senadi.pasantes.intranet.model.Kpi;
import com.senadi.pasantes.intranet.model.ObjetivoEspecifico;
import com.senadi.pasantes.intranet.repository.ActividadFechaRepository;
import com.senadi.pasantes.intranet.repository.ActividadRepository;
import com.senadi.pasantes.intranet.repository.KpiRepository;
import com.senadi.pasantes.intranet.repository.ObjetivoEspecificoRepository;
import com.senadi.pasantes.intranet.service.ActividadService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ActividadServiceImpl implements ActividadService {

    private final ActividadRepository actividadRepository;
    private final ObjetivoEspecificoRepository objetivoRepository;
    private final ActividadFechaRepository fechaRepository;
    private final KpiRepository kpiRepository;

    public ActividadServiceImpl(ActividadRepository actividadRepository,
                                ObjetivoEspecificoRepository objetivoRepository,
                                ActividadFechaRepository fechaRepository,
                                KpiRepository kpiRepository) {
        this.actividadRepository = actividadRepository;
        this.objetivoRepository = objetivoRepository;
        this.fechaRepository = fechaRepository;
        this.kpiRepository = kpiRepository;
    }

    @Override
    public List<Actividad> findByObjetivo(Long objetivoId) {
        return actividadRepository.findByObjetivoId(objetivoId);
    }

    @Override
    public Actividad findById(Long id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actividad no encontrada"));
    }

    @Override
    public Actividad save(Long objetivoId, Actividad actividad) {
        ObjetivoEspecifico objetivo = objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new EntityNotFoundException("Objetivo no encontrado"));
        actividad.setObjetivo(objetivo);
        return actividadRepository.save(actividad);
    }

    @Override
    public Actividad update(Long id, Actividad actividad) {
        Actividad existente = findById(id);
        existente.setNombre(actividad.getNombre());
        existente.setDescripcion(actividad.getDescripcion());
        existente.setCategoria(actividad.getCategoria());
        existente.setResultadoEsperado(actividad.getResultadoEsperado());
        existente.setResultadoObtenido(actividad.getResultadoObtenido());
        return actividadRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        actividadRepository.deleteById(id);
    }

    @Override
    public List<ActividadFecha> findFechas(Long actividadId) {
        return fechaRepository.findByActividadId(actividadId);
    }

    @Override
    public ActividadFecha addFecha(Long actividadId, ActividadFecha fecha) {
        Actividad actividad = findById(actividadId);
        fecha.setActividad(actividad);
        return fechaRepository.save(fecha);
    }

    @Override
    public void removeFecha(Long fechaId) {
        fechaRepository.deleteById(fechaId);
    }

    @Override
    public List<Kpi> findKpis(Long actividadId) {
        return kpiRepository.findByActividadId(actividadId);
    }

    @Override
    public Kpi addKpi(Long actividadId, Kpi kpi) {
        Actividad actividad = findById(actividadId);
        kpi.setActividad(actividad);
        return kpiRepository.save(kpi);
    }

    @Override
    public void removeKpi(Long kpiId) {
        kpiRepository.deleteById(kpiId);
    }
}
