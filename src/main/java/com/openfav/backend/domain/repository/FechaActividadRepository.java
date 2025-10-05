package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.FechaActividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FechaActividadRepository extends JpaRepository<FechaActividad, Long> {
    List<FechaActividad> findByActividadId(Long actividadId);
}
