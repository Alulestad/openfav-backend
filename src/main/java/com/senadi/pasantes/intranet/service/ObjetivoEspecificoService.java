package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.model.ObjetivoEspecifico;

import java.util.List;

public interface ObjetivoEspecificoService {
    List<ObjetivoEspecifico> findByProyecto(Long proyectoId);

    ObjetivoEspecifico findById(Long id);

    ObjetivoEspecifico save(Long proyectoId, ObjetivoEspecifico objetivo);

    ObjetivoEspecifico update(Long id, ObjetivoEspecifico objetivo);

    void delete(Long id);
}
