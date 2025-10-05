package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.SolicitudDesembolso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitudDesembolsoRepository extends JpaRepository<SolicitudDesembolso, Integer> {
    List<SolicitudDesembolso> findByPresupuesto_IdPres(Integer idPres);
    List<SolicitudDesembolso> findByPresupuesto_Proyecto_IdProy(Integer idProy);
}
