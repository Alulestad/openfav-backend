package com.openfav.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfav.dto.ActividadFechaRequest;
import com.openfav.dto.ActividadRequest;
import com.openfav.dto.KpiRequest;
import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.model.Actividad;
import com.openfav.model.ActividadFecha;
import com.openfav.model.Kpi;
import com.openfav.service.ActividadService;
import com.openfav.service.ProyectoService;

@RestController
@RequestMapping("/api")
public class ActividadController {

    private final ActividadService actividadService;
    private final ProyectoService proyectoService;

    public ActividadController(ActividadService actividadService, ProyectoService proyectoService) {
        this.actividadService = actividadService;
        this.proyectoService = proyectoService;
    }

    @PostMapping("/objectives/{objetivoId}/activities")
    public ResponseEntity<ProyectoDetalleDto> crearActividad(@PathVariable Integer objetivoId,
            @RequestBody ActividadRequest request) {
        Actividad actividad = actividadService.crearActividad(objetivoId, request);
        Integer proyectoId = actividad.getObjetivoEspecifico().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PutMapping("/activities/{actividadId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarActividad(@PathVariable Integer actividadId,
            @RequestBody ActividadRequest request) {
        Actividad actividad = actividadService.actualizarActividad(actividadId, request);
        Integer proyectoId = actividad.getObjetivoEspecifico().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @DeleteMapping("/activities/{actividadId}")
    public ResponseEntity<ProyectoDetalleDto> eliminarActividad(@PathVariable Integer actividadId) {
        Actividad actividad = actividadService.obtenerActividad(actividadId);
        Integer proyectoId = actividad.getObjetivoEspecifico().getProyecto().getId();
        actividadService.eliminarActividad(actividadId);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PostMapping("/activities/{actividadId}/schedules")
    public ResponseEntity<ProyectoDetalleDto> agregarFecha(@PathVariable Integer actividadId,
            @RequestBody ActividadFechaRequest request) {
        ActividadFecha fecha = actividadService.agregarFecha(actividadId, request);
        Integer proyectoId = fecha.getActividad().getObjetivoEspecifico().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PutMapping("/schedules/{fechaId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarFecha(@PathVariable Integer fechaId,
            @RequestBody ActividadFechaRequest request) {
        ActividadFecha fecha = actividadService.actualizarFecha(fechaId, request);
        Integer proyectoId = fecha.getActividad().getObjetivoEspecifico().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @DeleteMapping("/schedules/{fechaId}")
    public ResponseEntity<ProyectoDetalleDto> eliminarFecha(@PathVariable Integer fechaId) {
        ActividadFecha fecha = actividadService.obtenerFecha(fechaId);
        Integer proyectoId = fecha.getActividad().getObjetivoEspecifico().getProyecto().getId();
        actividadService.eliminarFecha(fechaId);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PostMapping("/activities/{actividadId}/kpis")
    public ResponseEntity<ProyectoDetalleDto> agregarKpi(@PathVariable Integer actividadId,
            @RequestBody KpiRequest request) {
        Kpi kpi = actividadService.agregarKpi(actividadId, request);
        Integer proyectoId = kpi.getActividad().getObjetivoEspecifico().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PutMapping("/kpis/{kpiId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarKpi(@PathVariable Integer kpiId,
            @RequestBody KpiRequest request) {
        Kpi kpi = actividadService.actualizarKpi(kpiId, request);
        Integer proyectoId = kpi.getActividad().getObjetivoEspecifico().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @DeleteMapping("/kpis/{kpiId}")
    public ResponseEntity<ProyectoDetalleDto> eliminarKpi(@PathVariable Integer kpiId) {
        Kpi kpi = actividadService.obtenerKpi(kpiId);
        Integer proyectoId = kpi.getActividad().getObjetivoEspecifico().getProyecto().getId();
        actividadService.eliminarKpi(kpiId);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }
}
