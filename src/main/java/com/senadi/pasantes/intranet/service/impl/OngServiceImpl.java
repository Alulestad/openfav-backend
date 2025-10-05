package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.Ong;
import com.senadi.pasantes.intranet.repository.OngRepository;
import com.senadi.pasantes.intranet.service.OngService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class OngServiceImpl implements OngService {

    private final OngRepository ongRepository;

    public OngServiceImpl(OngRepository ongRepository) {
        this.ongRepository = ongRepository;
    }

    @Override
    public List<Ong> findAll() {
        return ongRepository.findAll();
    }

    @Override
    public Ong findById(Long id) {
        return ongRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ONG no encontrada"));
    }

    @Override
    public Ong save(Ong ong) {
        return ongRepository.save(ong);
    }

    @Override
    public void delete(Long id) {
        ongRepository.deleteById(id);
    }
}
