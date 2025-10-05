package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.SolicitudDesembolso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudDesembolsoRepository extends JpaRepository<SolicitudDesembolso, Long> {
    List<SolicitudDesembolso> findByProyectoId(Long proyectoId);
}
