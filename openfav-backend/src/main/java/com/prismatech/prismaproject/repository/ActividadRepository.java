package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findByObjetivoEspecifico_IdObjesp(Integer idObjesp);
}
