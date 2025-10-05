package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.Presupuesto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {

    @EntityGraph(attributePaths = {"solicitudes"})
    List<Presupuesto> findByProyectoId(Long proyectoId);
}
