package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.Actividad;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    @EntityGraph(attributePaths = {"fechas", "kpis"})
    List<Actividad> findByObjetivoEspecificoId(Long objetivoId);
}
