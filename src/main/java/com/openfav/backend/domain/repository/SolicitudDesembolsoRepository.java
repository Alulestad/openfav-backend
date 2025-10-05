package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.SolicitudDesembolso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudDesembolsoRepository extends JpaRepository<SolicitudDesembolso, Long> {
    List<SolicitudDesembolso> findByPresupuestoId(Long presupuestoId);
}
