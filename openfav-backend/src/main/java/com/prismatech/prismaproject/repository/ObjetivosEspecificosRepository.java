package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.ObjetivosEspecificos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetivosEspecificosRepository extends JpaRepository<ObjetivosEspecificos, Integer> {
    List<ObjetivosEspecificos> findByProyecto_IdProy(Integer idProy);
}
