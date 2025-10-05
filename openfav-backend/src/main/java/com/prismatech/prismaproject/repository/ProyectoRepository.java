package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {
    List<Proyecto> findByOng_IdOng(Integer idOng);
}
