package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByOngId(Long ongId);
}
