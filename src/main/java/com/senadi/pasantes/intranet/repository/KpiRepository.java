package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.Kpi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KpiRepository extends JpaRepository<Kpi, Long> {
    List<Kpi> findByActividadId(Long actividadId);
}
