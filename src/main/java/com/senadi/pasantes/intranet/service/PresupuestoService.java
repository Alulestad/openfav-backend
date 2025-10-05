package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.model.PresupuestoItem;

import java.util.List;

public interface PresupuestoService {
    List<PresupuestoItem> findByProyecto(Long proyectoId);

    PresupuestoItem findById(Long id);

    PresupuestoItem save(Long proyectoId, PresupuestoItem item);

    PresupuestoItem update(Long id, PresupuestoItem item);

    void delete(Long id);
}
