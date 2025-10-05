package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.ActividadFecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadFechaRepository extends JpaRepository<ActividadFecha, Long> {
    List<ActividadFecha> findByActividadId(Long actividadId);
}
