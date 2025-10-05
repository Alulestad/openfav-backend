package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.Proyecto;
import com.senadi.pasantes.intranet.model.SolicitudDesembolso;
import com.senadi.pasantes.intranet.repository.ProyectoRepository;
import com.senadi.pasantes.intranet.repository.SolicitudDesembolsoRepository;
import com.senadi.pasantes.intranet.service.SolicitudDesembolsoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class SolicitudDesembolsoServiceImpl implements SolicitudDesembolsoService {

    private final SolicitudDesembolsoRepository solicitudRepository;
    private final ProyectoRepository proyectoRepository;

    public SolicitudDesembolsoServiceImpl(SolicitudDesembolsoRepository solicitudRepository,
                                          ProyectoRepository proyectoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<SolicitudDesembolso> findByProyecto(Long proyectoId) {
        return solicitudRepository.findByProyectoId(proyectoId);
    }

    @Override
    public SolicitudDesembolso findById(Long id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada"));
    }

    @Override
    public SolicitudDesembolso save(Long proyectoId, SolicitudDesembolso solicitud) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
        solicitud.setProyecto(proyecto);
        return solicitudRepository.save(solicitud);
    }

    @Override
    public SolicitudDesembolso update(Long id, SolicitudDesembolso solicitud) {
        SolicitudDesembolso existente = findById(id);
        existente.setDocumento(solicitud.getDocumento());
        existente.setEstado(solicitud.getEstado());
        return solicitudRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        solicitudRepository.deleteById(id);
    }
}
