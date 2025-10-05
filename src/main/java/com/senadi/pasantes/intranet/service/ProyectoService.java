package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.model.Proyecto;

import java.util.List;

public interface ProyectoService {
    List<Proyecto> findAll();

    List<Proyecto> findByOng(Long ongId);

    Proyecto findById(Long id);

    Proyecto save(Long ongId, Proyecto proyecto);

    Proyecto update(Long id, Proyecto proyecto);

    void delete(Long id);
}
