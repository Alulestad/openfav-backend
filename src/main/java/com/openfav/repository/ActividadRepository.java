package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.Actividad;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByObjetivoEspecificoId(Integer objetivoId);
}
