package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.Proyecto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    @EntityGraph(attributePaths = {
            "objetivosEspecificos",
            "objetivosEspecificos.actividades",
            "objetivosEspecificos.actividades.fechas",
            "objetivosEspecificos.actividades.kpis",
            "presupuestos",
            "presupuestos.solicitudes",
            "ongs"
    })
    Optional<Proyecto> findDetailedById(Long id);

    @EntityGraph(attributePaths = {"presupuestos", "presupuestos.solicitudes"})
    List<Proyecto> findByOngsId(Long ongId);
}
