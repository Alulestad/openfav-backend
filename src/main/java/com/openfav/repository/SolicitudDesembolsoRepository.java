package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openfav.model.SolicitudDesembolso;

public interface SolicitudDesembolsoRepository extends JpaRepository<SolicitudDesembolso, Integer> {

    List<SolicitudDesembolso> findByPresupuestoId(Integer presupuestoId);

    @Query("SELECT s FROM SolicitudDesembolso s WHERE s.presupuesto.proyecto.id = :proyectoId")
    List<SolicitudDesembolso> findByProyectoId(@Param("proyectoId") Integer proyectoId);
}
