package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.ObjetivoEspecifico;
import com.senadi.pasantes.intranet.model.Proyecto;
import com.senadi.pasantes.intranet.repository.ObjetivoEspecificoRepository;
import com.senadi.pasantes.intranet.repository.ProyectoRepository;
import com.senadi.pasantes.intranet.service.ObjetivoEspecificoService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ObjetivoEspecificoServiceImpl implements ObjetivoEspecificoService {

    private final ObjetivoEspecificoRepository objetivoRepository;
    private final ProyectoRepository proyectoRepository;

    public ObjetivoEspecificoServiceImpl(ObjetivoEspecificoRepository objetivoRepository,
                                         ProyectoRepository proyectoRepository) {
        this.objetivoRepository = objetivoRepository;
        this.proyectoRepository = proyectoRepository;
    }

    @Override
    public List<ObjetivoEspecifico> findByProyecto(Long proyectoId) {
        return objetivoRepository.findByProyectoId(proyectoId);
    }

    @Override
    public ObjetivoEspecifico findById(Long id) {
        return objetivoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Objetivo no encontrado"));
    }

    @Override
    public ObjetivoEspecifico save(Long proyectoId, ObjetivoEspecifico objetivo) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new EntityNotFoundException("Proyecto no encontrado"));
        objetivo.setProyecto(proyecto);
        return objetivoRepository.save(objetivo);
    }

    @Override
    public ObjetivoEspecifico update(Long id, ObjetivoEspecifico objetivo) {
        ObjetivoEspecifico existente = findById(id);
        existente.setObjetivo(objetivo.getObjetivo());
        existente.setEjes(objetivo.getEjes());
        return objetivoRepository.save(existente);
    }

    @Override
    public void delete(Long id) {
        objetivoRepository.deleteById(id);
    }
}
