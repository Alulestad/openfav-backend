package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KpiRepository extends JpaRepository<Kpi, Long> {
    List<Kpi> findByActividadId(Long actividadId);
}
