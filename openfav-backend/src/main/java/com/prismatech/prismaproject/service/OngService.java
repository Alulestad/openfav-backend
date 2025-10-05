package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.model.Ong;

import java.util.List;

public interface OngService {
    Ong create(Ong ong);
    Ong getById(Integer id);
    List<Ong> getAll();
    Ong update(Integer id, Ong ong);
    void delete(Integer id);
}
