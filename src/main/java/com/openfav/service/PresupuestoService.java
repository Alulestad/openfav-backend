package com.openfav.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.PresupuestoRequest;
import com.openfav.model.Presupuesto;
import com.openfav.model.Proyecto;
import com.openfav.repository.PresupuestoRepository;
import com.openfav.repository.ProyectoRepository;
import com.openfav.repository.SolicitudDesembolsoRepository;

@Service
public class PresupuestoService {

    private final PresupuestoRepository presupuestoRepository;
    private final ProyectoRepository proyectoRepository;
    private final SolicitudDesembolsoRepository solicitudRepository;

    public PresupuestoService(PresupuestoRepository presupuestoRepository,
            ProyectoRepository proyectoRepository,
            SolicitudDesembolsoRepository solicitudRepository) {
        this.presupuestoRepository = presupuestoRepository;
        this.proyectoRepository = proyectoRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional(readOnly = true)
    public List<Presupuesto> listarPorProyecto(Integer proyectoId) {
        return presupuestoRepository.findByProyectoId(proyectoId);
    }

    @Transactional
    public Presupuesto crear(Integer proyectoId, PresupuestoRequest request) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        Presupuesto presupuesto = new Presupuesto();
        presupuesto.setProyecto(proyecto);
        actualizarEntidadPresupuesto(presupuesto, request);
        return presupuestoRepository.save(presupuesto);
    }

    @Transactional
    public Presupuesto actualizar(Integer presupuestoId, PresupuestoRequest request) {
        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId)
                .orElseThrow(() -> new IllegalArgumentException("Presupuesto no encontrado"));
        actualizarEntidadPresupuesto(presupuesto, request);
        return presupuestoRepository.save(presupuesto);
    }

    @Transactional(readOnly = true)
    public Presupuesto obtener(Integer presupuestoId) {
        return presupuestoRepository.findById(presupuestoId)
                .orElseThrow(() -> new IllegalArgumentException("Presupuesto no encontrado"));
    }

    @Transactional
    public void eliminar(Integer presupuestoId) {
        Presupuesto presupuesto = obtener(presupuestoId);
        solicitudRepository.deleteAll(solicitudRepository.findByPresupuestoId(presupuesto.getId()));
        presupuestoRepository.delete(presupuesto);
    }

    private void actualizarEntidadPresupuesto(Presupuesto presupuesto, PresupuestoRequest request) {
        presupuesto.setCategoria(request.getCategoria());
        presupuesto.setCantidad(request.getCantidad());
        presupuesto.setUnidades(request.getUnidades());
        presupuesto.setValor(request.getValor());
    }
}
