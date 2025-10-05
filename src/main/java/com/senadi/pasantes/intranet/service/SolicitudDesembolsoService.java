package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.model.SolicitudDesembolso;

import java.util.List;

public interface SolicitudDesembolsoService {
    List<SolicitudDesembolso> findByProyecto(Long proyectoId);

    SolicitudDesembolso findById(Long id);

    SolicitudDesembolso save(Long proyectoId, SolicitudDesembolso solicitud);

    SolicitudDesembolso update(Long id, SolicitudDesembolso solicitud);

    void delete(Long id);
}
