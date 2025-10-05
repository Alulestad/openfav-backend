package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.OngProyecto;
import com.openfav.model.OngProyecto.OngProyectoId;

public interface OngProyectoRepository extends JpaRepository<OngProyecto, OngProyectoId> {
    List<OngProyecto> findByIdOngId(Integer ongId);
    void deleteByIdProyectoId(Integer proyectoId);
}
