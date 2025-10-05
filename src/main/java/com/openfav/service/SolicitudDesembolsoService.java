package com.openfav.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.SolicitudDesembolsoRequest;
import com.openfav.model.Presupuesto;
import com.openfav.model.SolicitudDesembolso;
import com.openfav.repository.PresupuestoRepository;
import com.openfav.repository.SolicitudDesembolsoRepository;

@Service
public class SolicitudDesembolsoService {

    private final SolicitudDesembolsoRepository solicitudRepository;
    private final PresupuestoRepository presupuestoRepository;

    public SolicitudDesembolsoService(SolicitudDesembolsoRepository solicitudRepository,
            PresupuestoRepository presupuestoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.presupuestoRepository = presupuestoRepository;
    }

    @Transactional(readOnly = true)
    public List<SolicitudDesembolso> listarPorProyecto(Integer proyectoId) {
        return solicitudRepository.findByProyectoId(proyectoId);
    }

    @Transactional
    public SolicitudDesembolso crear(SolicitudDesembolsoRequest request) {
        Presupuesto presupuesto = presupuestoRepository.findById(request.getPresupuestoId())
                .orElseThrow(() -> new IllegalArgumentException("Presupuesto no encontrado"));
        SolicitudDesembolso solicitud = new SolicitudDesembolso();
        solicitud.setPresupuesto(presupuesto);
        actualizarEntidadSolicitud(solicitud, request);
        return solicitudRepository.save(solicitud);
    }

    @Transactional
    public SolicitudDesembolso actualizar(Integer solicitudId, SolicitudDesembolsoRequest request) {
        SolicitudDesembolso solicitud = solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
        if (request.getPresupuestoId() != null) {
            Presupuesto presupuesto = presupuestoRepository.findById(request.getPresupuestoId())
                    .orElseThrow(() -> new IllegalArgumentException("Presupuesto no encontrado"));
            solicitud.setPresupuesto(presupuesto);
        }
        actualizarEntidadSolicitud(solicitud, request);
        return solicitudRepository.save(solicitud);
    }

    @Transactional(readOnly = true)
    public SolicitudDesembolso obtener(Integer solicitudId) {
        return solicitudRepository.findById(solicitudId)
                .orElseThrow(() -> new IllegalArgumentException("Solicitud no encontrada"));
    }

    @Transactional
    public void eliminar(Integer solicitudId) {
        SolicitudDesembolso solicitud = obtener(solicitudId);
        solicitudRepository.delete(solicitud);
    }

    private void actualizarEntidadSolicitud(SolicitudDesembolso solicitud, SolicitudDesembolsoRequest request) {
        solicitud.setDocumento(request.getDocumento());
        solicitud.setEstado(request.getEstado());
    }
}
