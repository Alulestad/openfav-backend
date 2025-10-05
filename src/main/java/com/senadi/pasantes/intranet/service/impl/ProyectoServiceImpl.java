package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.Ong;
import com.senadi.pasantes.intranet.model.Proyecto;
import com.senadi.pasantes.intranet.repository.OngRepository;
import com.senadi.pasantes.intranet.repository.ProyectoRepository;
import com.senadi.pasantes.intranet.service.ProyectoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final OngRepository ongRepository;

    public ProyectoServiceImpl(ProyectoRepository proyectoRepository, OngRepository ongRepository) {
        this.proyectoRepository = proyectoRepository;
        this.ongRepository = ongRepository;
    }

    @Override
    public List<Proyecto> findAll() {
        return proyectoRepository.findAll();
    }

    @Override
    public List<Proyecto> findByOng(Long ongId) {
        return proyectoRepository.findByOngId(ongId);
    }

    @Override
    public Proyecto findById(Long id) {
        return proyectoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
    }

    @Override
    public Proyecto save(Long ongId, Proyecto proyecto) {
        Ong ong = ongRepository.findById(ongId)
                .orElseThrow(() -> new EntityNotFoundException("ONG no encontrada"));
        proyecto.setOng(ong);
        return proyectoRepository.save(proyecto);
    }

    @Override
    public Proyecto update(Long id, Proyecto proyecto) {
        Proyecto existente = findById(id);
        existente.setTitulo(proyecto.getTitulo());
        existente.setObjetivoGeneral(proyecto.getObjetivoGeneral());
        existente.setAlcance(proyecto.getAlcance());
        existente.setMontoTotal(proyecto.getMontoTotal());
        existente.setPlazoTotal(proyecto.getPlazoTotal());
        existente.setFechaInicio(proyecto.getFechaInicio());
        existente.setFechaFin(proyecto.getFechaFin());
        existente.setEjes(proyecto.getEjes());
        existente.setResultado(proyecto.getResultado());
        return proyectoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        proyectoRepository.deleteById(id);
    }
}
