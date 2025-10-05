package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.ObjetivoEspecifico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetivoEspecificoRepository extends JpaRepository<ObjetivoEspecifico, Long> {
    List<ObjetivoEspecifico> findByProyectoId(Long proyectoId);
}
