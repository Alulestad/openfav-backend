package com.openfav.backend.service.mapper;

import com.openfav.backend.domain.model.FechaActividad;
import com.openfav.backend.domain.model.Ong;
import com.openfav.backend.domain.model.Presupuesto;
import com.openfav.backend.domain.model.Proyecto;
import com.openfav.backend.service.dto.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public final class OngMapper {

    private OngMapper() {
    }

    public static OngProfileDto toProfileDto(Ong ong) {
        return new OngProfileDto(
                ong.getId(),
                ong.getNombre(),
                ong.getRepresentanteLegal(),
                ong.getEmail(),
                ong.getCelular(),
                ong.getDireccion()
        );
    }

    public static ProjectSummaryDto toProjectSummaryDto(Proyecto proyecto) {
        String descripcion = proyecto.getObjetivoGeneral();
        if (descripcion != null && descripcion.length() > 120) {
            descripcion = descripcion.substring(0, 117) + "...";
        }
        return new ProjectSummaryDto(
                proyecto.getId(),
                proyecto.getTitulo(),
                descripcion,
                proyecto.getFechaInicio(),
                proyecto.getFechaFin()
        );
    }

    public static ProjectDetailDto toProjectDetailDto(Proyecto proyecto) {
        List<ObjetivoEspecificoDto> objetivos = proyecto.getObjetivosEspecificos()
                .stream()
                .sorted(Comparator.comparingLong(o -> o.getId() == null ? 0 : o.getId()))
                .map(OngMapper::toObjectiveDto)
                .toList();

        List<PresupuestoDto> presupuestos = proyecto.getPresupuestos()
                .stream()
                .sorted(Comparator.comparingLong(p -> p.getId() == null ? 0 : p.getId()))
                .map(OngMapper::toPresupuestoDto)
                .toList();

        return new ProjectDetailDto(
                proyecto.getId(),
                proyecto.getTitulo(),
                proyecto.getObjetivoGeneral(),
                proyecto.getAlcance(),
                proyecto.getMontoTotal(),
                proyecto.getPlazoTotal(),
                proyecto.getFechaInicio(),
                proyecto.getFechaFin(),
                proyecto.getEjes(),
                proyecto.getResultado(),
                objetivos,
                presupuestos
        );
    }

    public static ObjetivoEspecificoDto toObjectiveDto(com.openfav.backend.domain.model.ObjetivoEspecifico objetivo) {
        List<ActivityDto> actividades = objetivo.getActividades()
                .stream()
                .sorted(Comparator.comparingLong(a -> a.getId() == null ? 0 : a.getId()))
                .map(OngMapper::toActivityDto)
                .toList();

        return new ObjetivoEspecificoDto(
                objetivo.getId(),
                objetivo.getObjetivo(),
                objetivo.getEjes(),
                actividades
        );
    }

    public static ActivityDto toActivityDto(com.openfav.backend.domain.model.Actividad actividad) {
        Comparator<FechaActividad> fechasComparator = Comparator
                .comparing(FechaActividad::getFechaInicio, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(FechaActividad::getId, Comparator.nullsLast(Comparator.naturalOrder()));

        List<ActivityScheduleDto> fechas = actividad.getFechas().stream()
                .sorted(fechasComparator)
                .map(fecha -> new ActivityScheduleDto(
                        fecha.getId(),
                        fecha.getFechaInicio(),
                        fecha.getFechaFin()
                ))
                .toList();

        List<KpiDto> kpis = actividad.getKpis().stream()
                .sorted(Comparator.comparingLong(k -> k.getId() == null ? 0 : k.getId()))
                .map(kpi -> new KpiDto(kpi.getId(), kpi.getValor(), kpi.getDescripcion()))
                .toList();

        return new ActivityDto(
                actividad.getId(),
                actividad.getNombre(),
                actividad.getDescripcion(),
                actividad.getResultadoEsperado(),
                actividad.getResultadoObtenido(),
                actividad.getCategoria(),
                fechas,
                kpis
        );
    }

    public static PresupuestoDto toPresupuestoDto(Presupuesto presupuesto) {
        List<SolicitudDesembolsoDto> solicitudes = presupuesto.getSolicitudes().stream()
                .sorted(Comparator.comparingLong(s -> s.getId() == null ? 0 : s.getId()))
                .map(s -> new SolicitudDesembolsoDto(s.getId(), s.getDocumento(), s.getEstado()))
                .collect(Collectors.toList());

        return new PresupuestoDto(
                presupuesto.getId(),
                presupuesto.getCategoria(),
                presupuesto.getCantidad(),
                presupuesto.getUnidades(),
                presupuesto.getValor(),
                solicitudes
        );
    }
}
