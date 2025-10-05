package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.Kpi;

public interface KpiRepository extends JpaRepository<Kpi, Integer> {
    List<Kpi> findByActividadId(Integer actividadId);
}
