package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.model.Actividad;
import com.senadi.pasantes.intranet.model.ActividadFecha;
import com.senadi.pasantes.intranet.model.Kpi;

import java.util.List;

public interface ActividadService {
    List<Actividad> findByObjetivo(Long objetivoId);

    Actividad findById(Long id);

    Actividad save(Long objetivoId, Actividad actividad);

    Actividad update(Long id, Actividad actividad);

    void delete(Long id);

    List<ActividadFecha> findFechas(Long actividadId);

    ActividadFecha addFecha(Long actividadId, ActividadFecha fecha);

    void removeFecha(Long fechaId);

    List<Kpi> findKpis(Long actividadId);

    Kpi addKpi(Long actividadId, Kpi kpi);

    void removeKpi(Long kpiId);
}
