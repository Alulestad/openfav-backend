package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.Presupuesto;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {
    List<Presupuesto> findByProyectoId(Integer proyectoId);
}
