package com.openfav.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.ActividadFechaRequest;
import com.openfav.dto.ActividadRequest;
import com.openfav.dto.KpiRequest;
import com.openfav.model.Actividad;
import com.openfav.model.ActividadFecha;
import com.openfav.model.Kpi;
import com.openfav.model.ObjetivoEspecifico;
import com.openfav.repository.ActividadFechaRepository;
import com.openfav.repository.ActividadRepository;
import com.openfav.repository.KpiRepository;
import com.openfav.repository.ObjetivoEspecificoRepository;

@Service
public class ActividadService {

    private final ActividadRepository actividadRepository;
    private final ObjetivoEspecificoRepository objetivoRepository;
    private final ActividadFechaRepository fechaRepository;
    private final KpiRepository kpiRepository;

    public ActividadService(ActividadRepository actividadRepository,
            ObjetivoEspecificoRepository objetivoRepository,
            ActividadFechaRepository fechaRepository,
            KpiRepository kpiRepository) {
        this.actividadRepository = actividadRepository;
        this.objetivoRepository = objetivoRepository;
        this.fechaRepository = fechaRepository;
        this.kpiRepository = kpiRepository;
    }

    @Transactional
    public Actividad crearActividad(Integer objetivoId, ActividadRequest request) {
        ObjetivoEspecifico objetivo = objetivoRepository.findById(objetivoId)
                .orElseThrow(() -> new IllegalArgumentException("Objetivo específico no encontrado"));
        Actividad actividad = new Actividad();
        actividad.setObjetivoEspecifico(objetivo);
        actualizarEntidadActividad(actividad, request);
        return actividadRepository.save(actividad);
    }

    @Transactional
    public Actividad actualizarActividad(Integer actividadId, ActividadRequest request) {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        actualizarEntidadActividad(actividad, request);
        return actividadRepository.save(actividad);
    }

    @Transactional(readOnly = true)
    public Actividad obtenerActividad(Integer actividadId) {
        return actividadRepository.findById(actividadId)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
    }

    @Transactional
    public void eliminarActividad(Integer actividadId) {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        fechaRepository.deleteAll(fechaRepository.findByActividadId(actividad.getId()));
        kpiRepository.deleteAll(kpiRepository.findByActividadId(actividad.getId()));
        actividadRepository.delete(actividad);
    }

    @Transactional
    public ActividadFecha agregarFecha(Integer actividadId, ActividadFechaRequest request) {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        ActividadFecha fecha = new ActividadFecha();
        fecha.setActividad(actividad);
        fecha.setFechaInicio(request.getFechaInicio());
        fecha.setFechaFin(request.getFechaFin());
        return fechaRepository.save(fecha);
    }

    @Transactional
    public ActividadFecha actualizarFecha(Integer fechaId, ActividadFechaRequest request) {
        ActividadFecha fecha = fechaRepository.findById(fechaId)
                .orElseThrow(() -> new IllegalArgumentException("Calendario de actividad no encontrado"));
        fecha.setFechaInicio(request.getFechaInicio());
        fecha.setFechaFin(request.getFechaFin());
        return fechaRepository.save(fecha);
    }

    @Transactional(readOnly = true)
    public ActividadFecha obtenerFecha(Integer fechaId) {
        return fechaRepository.findById(fechaId)
                .orElseThrow(() -> new IllegalArgumentException("Calendario de actividad no encontrado"));
    }

    @Transactional
    public void eliminarFecha(Integer fechaId) {
        ActividadFecha fecha = fechaRepository.findById(fechaId)
                .orElseThrow(() -> new IllegalArgumentException("Calendario de actividad no encontrado"));
        fechaRepository.delete(fecha);
    }

    @Transactional
    public Kpi agregarKpi(Integer actividadId, KpiRequest request) {
        Actividad actividad = actividadRepository.findById(actividadId)
                .orElseThrow(() -> new IllegalArgumentException("Actividad no encontrada"));
        Kpi kpi = new Kpi();
        kpi.setActividad(actividad);
        actualizarEntidadKpi(kpi, request);
        return kpiRepository.save(kpi);
    }

    @Transactional
    public Kpi actualizarKpi(Integer kpiId, KpiRequest request) {
        Kpi kpi = kpiRepository.findById(kpiId)
                .orElseThrow(() -> new IllegalArgumentException("KPI no encontrado"));
        actualizarEntidadKpi(kpi, request);
        return kpiRepository.save(kpi);
    }

    @Transactional(readOnly = true)
    public Kpi obtenerKpi(Integer kpiId) {
        return kpiRepository.findById(kpiId)
                .orElseThrow(() -> new IllegalArgumentException("KPI no encontrado"));
    }

    @Transactional
    public void eliminarKpi(Integer kpiId) {
        Kpi kpi = kpiRepository.findById(kpiId)
                .orElseThrow(() -> new IllegalArgumentException("KPI no encontrado"));
        kpiRepository.delete(kpi);
    }

    private void actualizarEntidadActividad(Actividad actividad, ActividadRequest request) {
        actividad.setNombre(request.getNombre());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setResultadoEsperado(request.getResultadoEsperado());
        actividad.setResultadoObtenido(request.getResultadoObtenido());
        actividad.setCategoria(request.getCategoria());
    }

    private void actualizarEntidadKpi(Kpi kpi, KpiRequest request) {
        kpi.setValor(request.getValor());
        kpi.setDescripcion(request.getDescripcion());
    }
}
