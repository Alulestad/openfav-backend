package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.ObjetivoEspecifico;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObjetivoEspecificoRepository extends JpaRepository<ObjetivoEspecifico, Long> {

    @EntityGraph(attributePaths = {"actividades", "actividades.fechas", "actividades.kpis"})
    Optional<ObjetivoEspecifico> findWithTreeById(Long id);

    List<ObjetivoEspecifico> findByProyectoId(Long proyectoId);
}
