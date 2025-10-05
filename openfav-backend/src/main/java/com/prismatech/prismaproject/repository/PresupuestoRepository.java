package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.Presupuesto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoRepository extends JpaRepository<Presupuesto, Integer> {
    List<Presupuesto> findByProyecto_IdProy(Integer idProy);
}
