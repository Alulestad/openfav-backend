package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.ObjetivoEspecifico;

public interface ObjetivoEspecificoRepository extends JpaRepository<ObjetivoEspecifico, Integer> {
    List<ObjetivoEspecifico> findByProyectoId(Integer proyectoId);
}
