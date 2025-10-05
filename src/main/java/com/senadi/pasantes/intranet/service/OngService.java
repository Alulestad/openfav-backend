package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.model.Ong;

import java.util.List;

public interface OngService {
    List<Ong> findAll();

    Ong findById(Long id);

    Ong save(Ong ong);

    void delete(Long id);
}
