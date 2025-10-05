package com.openfav.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.openfav.model.Proyecto;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    @Query("SELECT op.proyecto FROM OngProyecto op WHERE op.id.ongId = :ongId")
    List<Proyecto> findByOngId(@Param("ongId") Integer ongId);
}
