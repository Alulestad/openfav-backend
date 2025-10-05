package com.openfav.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.dto.SolicitudDesembolsoDto;
import com.openfav.dto.SolicitudDesembolsoRequest;
import com.openfav.model.SolicitudDesembolso;
import com.openfav.service.ProyectoService;
import com.openfav.service.SolicitudDesembolsoService;

@RestController
@RequestMapping("/api")
public class SolicitudDesembolsoController {

    private final SolicitudDesembolsoService solicitudService;
    private final ProyectoService proyectoService;

    public SolicitudDesembolsoController(SolicitudDesembolsoService solicitudService, ProyectoService proyectoService) {
        this.solicitudService = solicitudService;
        this.proyectoService = proyectoService;
    }

    @GetMapping("/projects/{proyectoId}/budget-requests")
    public ResponseEntity<List<SolicitudDesembolsoDto>> listarSolicitudes(@PathVariable Integer proyectoId) {
        List<SolicitudDesembolso> solicitudes = solicitudService.listarPorProyecto(proyectoId);
        List<SolicitudDesembolsoDto> respuesta = solicitudes.stream()
                .map(this::mapSolicitud)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/budget-requests")
    public ResponseEntity<ProyectoDetalleDto> crearSolicitud(@RequestBody SolicitudDesembolsoRequest request) {
        SolicitudDesembolso solicitud = solicitudService.crear(request);
        Integer proyectoId = solicitud.getPresupuesto().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PutMapping("/budget-requests/{solicitudId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarSolicitud(@PathVariable Integer solicitudId,
            @RequestBody SolicitudDesembolsoRequest request) {
        SolicitudDesembolso solicitud = solicitudService.actualizar(solicitudId, request);
        Integer proyectoId = solicitud.getPresupuesto().getProyecto().getId();
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @DeleteMapping("/budget-requests/{solicitudId}")
    public ResponseEntity<ProyectoDetalleDto> eliminarSolicitud(@PathVariable Integer solicitudId) {
        SolicitudDesembolso solicitud = solicitudService.obtener(solicitudId);
        Integer proyectoId = solicitud.getPresupuesto().getProyecto().getId();
        solicitudService.eliminar(solicitudId);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    private SolicitudDesembolsoDto mapSolicitud(SolicitudDesembolso solicitud) {
        SolicitudDesembolsoDto dto = new SolicitudDesembolsoDto();
        dto.setId(solicitud.getId());
        dto.setPresupuestoId(solicitud.getPresupuesto() != null ? solicitud.getPresupuesto().getId() : null);
        dto.setDocumento(solicitud.getDocumento());
        dto.setEstado(solicitud.getEstado());
        return dto;
    }
}
