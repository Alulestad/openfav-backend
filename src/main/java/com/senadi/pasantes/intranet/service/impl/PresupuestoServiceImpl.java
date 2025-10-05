package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.PresupuestoItem;
import com.senadi.pasantes.intranet.model.Proyecto;
import com.senadi.pasantes.intranet.repository.PresupuestoItemRepository;
import com.senadi.pasantes.intranet.repository.ProyectoRepository;
import com.senadi.pasantes.intranet.service.PresupuestoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PresupuestoServiceImpl implements PresupuestoService {

    private final PresupuestoItemRepository presupuestoRepository;
    private final ProyectoRepository proyectoRepository;

    public PresupuestoServiceImpl(PresupuestoItemRepository presupuestoRepository,
                                  ProyectoRepository proyectoRepository) {
        this.presupuestoRepository = presupuestoRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<PresupuestoItem> findByProyecto(Long proyectoId) {
        return presupuestoRepository.findByProyectoId(proyectoId);
    }

    @Override
    public PresupuestoItem findById(Long id) {
        return presupuestoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Item de presupuesto no encontrado"));
    }

    @Override
    public PresupuestoItem save(Long proyectoId, PresupuestoItem item) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
        item.setProyecto(proyecto);
        return presupuestoRepository.save(item);
    }

    @Override
    public PresupuestoItem update(Long id, PresupuestoItem item) {
        PresupuestoItem existente = findById(id);
        existente.setCategoria(item.getCategoria());
        existente.setCantidad(item.getCantidad());
        existente.setUnidades(item.getUnidades());
        existente.setValor(item.getValor());
        return presupuestoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        presupuestoRepository.deleteById(id);
    }
}
