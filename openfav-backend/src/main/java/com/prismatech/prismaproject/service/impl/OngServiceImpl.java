package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.model.Ong;
import com.prismatech.prismaproject.repository.OngRepository;
import com.prismatech.prismaproject.service.OngService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OngServiceImpl implements OngService {
    private final OngRepository repository;

    public OngServiceImpl(OngRepository repository) {
        this.repository = repository;
    }

    @Override
    public Ong create(Ong ong) {
        return repository.save(ong);
    }

    @Override
    public Ong getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Ong> getAll() {
        return repository.findAll();
    }

    @Override
    public Ong update(Integer id, Ong ong) {
        ong.setIdOng(id);
        return repository.save(ong);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
