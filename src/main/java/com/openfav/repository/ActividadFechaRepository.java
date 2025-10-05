package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.ActividadFecha;

public interface ActividadFechaRepository extends JpaRepository<ActividadFecha, Integer> {
    List<ActividadFecha> findByActividadId(Integer actividadId);
}
