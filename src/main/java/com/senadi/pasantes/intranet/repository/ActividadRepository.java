package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadRepository extends JpaRepository<Actividad, Long> {
    List<Actividad> findByObjetivoId(Long objetivoId);
}
