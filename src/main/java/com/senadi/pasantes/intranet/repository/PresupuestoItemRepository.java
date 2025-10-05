package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.PresupuestoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PresupuestoItemRepository extends JpaRepository<PresupuestoItem, Long> {
    List<PresupuestoItem> findByProyectoId(Long proyectoId);
}
