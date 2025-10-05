package com.openfav.backend.service;

import com.openfav.backend.domain.model.*;
import com.openfav.backend.domain.repository.*;
import com.openfav.backend.exception.BadRequestException;
import com.openfav.backend.exception.NotFoundException;
import com.openfav.backend.service.dto.*;
import com.openfav.backend.service.mapper.OngMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OngService {

    private final OngRepository ongRepository;
    private final ProyectoRepository proyectoRepository;
    private final ObjetivoEspecificoRepository objetivoEspecificoRepository;
    private final ActividadRepository actividadRepository;
    private final FechaActividadRepository fechaActividadRepository;
    private final KpiRepository kpiRepository;
    private final PresupuestoRepository presupuestoRepository;
    private final SolicitudDesembolsoRepository solicitudDesembolsoRepository;

    public OngService(OngRepository ongRepository,
                      ProyectoRepository proyectoRepository,
                      ObjetivoEspecificoRepository objetivoEspecificoRepository,
                      ActividadRepository actividadRepository,
                      FechaActividadRepository fechaActividadRepository,
                      KpiRepository kpiRepository,
                      PresupuestoRepository presupuestoRepository,
                      SolicitudDesembolsoRepository solicitudDesembolsoRepository) {
        this.ongRepository = ongRepository;
        this.proyectoRepository = proyectoRepository;
        this.objetivoEspecificoRepository = objetivoEspecificoRepository;
        this.actividadRepository = actividadRepository;
        this.fechaActividadRepository = fechaActividadRepository;
        this.kpiRepository = kpiRepository;
        this.presupuestoRepository = presupuestoRepository;
        this.solicitudDesembolsoRepository = solicitudDesembolsoRepository;
    }

    public OngProfileDto getProfile(String email) {
        Ong ong = getOngByEmail(email);
        return OngMapper.toProfileDto(ong);
    }

    public List<ProjectSummaryDto> listProjects(String email) {
        Ong ong = getOngByEmail(email);
        return proyectoRepository.findByOngsId(ong.getId()).stream()
                .sorted(Comparator.comparing(Proyecto::getFechaInicio, Comparator.nullsLast(Comparator.naturalOrder())))
                .map(OngMapper::toProjectSummaryDto)
                .toList();
    }

    public ProjectDetailDto createProject(String email, ProjectRequest request) {
        Ong ong = getOngByEmail(email);
        Proyecto proyecto = new Proyecto();
        applyProjectRequest(proyecto, request);
        Proyecto saved = proyectoRepository.save(proyecto);
        saved.getOngs().add(ong);
        ong.getProyectos().add(saved);
        ongRepository.save(ong);
        return getProjectDetail(saved.getId(), email);
    }

    public ProjectDetailDto updateProject(Long projectId, String email, ProjectRequest request) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        applyProjectRequest(proyecto, request);
        return OngMapper.toProjectDetailDto(proyecto);
    }

    public void deleteProject(Long projectId, String email) {
        Ong ong = getOngByEmail(email);
        Proyecto proyecto = proyectoRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
        if (proyecto.getOngs().stream().noneMatch(o -> Objects.equals(o.getId(), ong.getId()))) {
            throw new NotFoundException("El proyecto no pertenece a la ONG autenticada");
        }
        ong.getProyectos().removeIf(p -> Objects.equals(p.getId(), projectId));
        proyecto.getOngs().removeIf(o -> Objects.equals(o.getId(), ong.getId()));
        ongRepository.save(ong);
        proyectoRepository.delete(proyecto);
    }

    public ProjectDetailDto getProjectDetail(Long projectId, String email) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        return OngMapper.toProjectDetailDto(proyecto);
    }

    public ObjetivoEspecificoDto addObjective(Long projectId, String email, ObjetivoEspecificoRequest request) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        ObjetivoEspecifico objetivo = new ObjetivoEspecifico();
        objetivo.setObjetivo(request.objetivo());
        objetivo.setEjes(request.ejes());
        objetivo.setProyecto(proyecto);
        objetivoEspecificoRepository.save(objetivo);
        proyecto.getObjetivosEspecificos().add(objetivo);
        return OngMapper.toObjectiveDto(objetivo);
    }

    public ObjetivoEspecificoDto updateObjective(Long projectId, Long objectiveId, String email, ObjetivoEspecificoRequest request) {
        ObjetivoEspecifico objetivo = getObjectiveForProject(projectId, objectiveId, email);
        objetivo.setObjetivo(request.objetivo());
        objetivo.setEjes(request.ejes());
        return OngMapper.toObjectiveDto(objetivo);
    }

    public void deleteObjective(Long projectId, Long objectiveId, String email) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        ObjetivoEspecifico objetivo = objetivoEspecificoRepository.findById(objectiveId)
                .orElseThrow(() -> new NotFoundException("Objetivo específico no encontrado"));
        if (!Objects.equals(objetivo.getProyecto().getId(), proyecto.getId())) {
            throw new NotFoundException("Objetivo específico no pertenece al proyecto");
        }
        proyecto.getObjetivosEspecificos().removeIf(o -> Objects.equals(o.getId(), objectiveId));
        objetivoEspecificoRepository.delete(objetivo);
    }

    public ActivityDto addActivity(Long projectId, Long objectiveId, String email, ActividadRequest request) {
        ObjetivoEspecifico objetivo = getObjectiveForProject(projectId, objectiveId, email);
        Actividad actividad = new Actividad();
        applyActividadRequest(actividad, request);
        actividad.setObjetivoEspecifico(objetivo);
        actividadRepository.save(actividad);
        objetivo.getActividades().add(actividad);
        return OngMapper.toActivityDto(actividad);
    }

    public ActivityDto updateActivity(Long projectId, Long objectiveId, Long activityId, String email, ActividadRequest request) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        applyActividadRequest(actividad, request);
        return OngMapper.toActivityDto(actividad);
    }

    public void deleteActivity(Long projectId, Long objectiveId, Long activityId, String email) {
        ObjetivoEspecifico objetivo = getObjectiveForProject(projectId, objectiveId, email);
        Actividad actividad = actividadRepository.findById(activityId)
                .orElseThrow(() -> new NotFoundException("Actividad no encontrada"));
        if (!Objects.equals(actividad.getObjetivoEspecifico().getId(), objetivo.getId())) {
            throw new NotFoundException("La actividad no pertenece al objetivo específico");
        }
        objetivo.getActividades().removeIf(a -> Objects.equals(a.getId(), activityId));
        actividadRepository.delete(actividad);
    }

    public ActivityScheduleDto addSchedule(Long projectId, Long objectiveId, Long activityId, String email, ActivityScheduleRequest request) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        FechaActividad fecha = new FechaActividad();
        fecha.setActividad(actividad);
        fecha.setFechaInicio(request.fechaInicio());
        fecha.setFechaFin(request.fechaFin());
        fechaActividadRepository.save(fecha);
        actividad.getFechas().add(fecha);
        return new ActivityScheduleDto(fecha.getId(), fecha.getFechaInicio(), fecha.getFechaFin());
    }

    public ActivityScheduleDto updateSchedule(Long projectId, Long objectiveId, Long activityId, Long scheduleId, String email, ActivityScheduleRequest request) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        FechaActividad fecha = fechaActividadRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Cronograma de actividad no encontrado"));
        if (!Objects.equals(fecha.getActividad().getId(), actividad.getId())) {
            throw new NotFoundException("El cronograma no pertenece a la actividad");
        }
        fecha.setFechaInicio(request.fechaInicio());
        fecha.setFechaFin(request.fechaFin());
        return new ActivityScheduleDto(fecha.getId(), fecha.getFechaInicio(), fecha.getFechaFin());
    }

    public void deleteSchedule(Long projectId, Long objectiveId, Long activityId, Long scheduleId, String email) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        FechaActividad fecha = fechaActividadRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Cronograma de actividad no encontrado"));
        if (!Objects.equals(fecha.getActividad().getId(), actividad.getId())) {
            throw new NotFoundException("El cronograma no pertenece a la actividad");
        }
        actividad.getFechas().removeIf(f -> Objects.equals(f.getId(), scheduleId));
        fechaActividadRepository.delete(fecha);
    }

    public KpiDto addKpi(Long projectId, Long objectiveId, Long activityId, String email, KpiRequest request) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        Kpi kpi = new Kpi();
        kpi.setActividad(actividad);
        kpi.setValor(request.valor());
        kpi.setDescripcion(request.descripcion());
        kpiRepository.save(kpi);
        actividad.getKpis().add(kpi);
        return new KpiDto(kpi.getId(), kpi.getValor(), kpi.getDescripcion());
    }

    public KpiDto updateKpi(Long projectId, Long objectiveId, Long activityId, Long kpiId, String email, KpiRequest request) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        Kpi kpi = kpiRepository.findById(kpiId)
                .orElseThrow(() -> new NotFoundException("KPI no encontrado"));
        if (!Objects.equals(kpi.getActividad().getId(), actividad.getId())) {
            throw new NotFoundException("El KPI no pertenece a la actividad");
        }
        kpi.setValor(request.valor());
        kpi.setDescripcion(request.descripcion());
        return new KpiDto(kpi.getId(), kpi.getValor(), kpi.getDescripcion());
    }

    public void deleteKpi(Long projectId, Long objectiveId, Long activityId, Long kpiId, String email) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        Kpi kpi = kpiRepository.findById(kpiId)
                .orElseThrow(() -> new NotFoundException("KPI no encontrado"));
        if (!Objects.equals(kpi.getActividad().getId(), actividad.getId())) {
            throw new NotFoundException("El KPI no pertenece a la actividad");
        }
        actividad.getKpis().removeIf(k -> Objects.equals(k.getId(), kpiId));
        kpiRepository.delete(kpi);
    }

    public PresupuestoDto addBudget(Long projectId, String email, PresupuestoRequest request) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        Presupuesto presupuesto = new Presupuesto();
        applyPresupuestoRequest(presupuesto, request);
        presupuesto.setProyecto(proyecto);
        presupuestoRepository.save(presupuesto);
        proyecto.getPresupuestos().add(presupuesto);
        return OngMapper.toPresupuestoDto(presupuesto);
    }

    public PresupuestoDto updateBudget(Long projectId, Long presupuestoId, String email, PresupuestoRequest request) {
        Presupuesto presupuesto = getPresupuestoForProject(projectId, presupuestoId, email);
        applyPresupuestoRequest(presupuesto, request);
        return OngMapper.toPresupuestoDto(presupuesto);
    }

    public void deleteBudget(Long projectId, Long presupuestoId, String email) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        Presupuesto presupuesto = presupuestoRepository.findById(presupuestoId)
                .orElseThrow(() -> new NotFoundException("Partida de presupuesto no encontrada"));
        if (!Objects.equals(presupuesto.getProyecto().getId(), proyecto.getId())) {
            throw new NotFoundException("La partida de presupuesto no pertenece al proyecto");
        }
        if (!presupuesto.getSolicitudes().isEmpty()) {
            throw new BadRequestException("No se puede eliminar un presupuesto con solicitudes registradas");
        }
        proyecto.getPresupuestos().removeIf(p -> Objects.equals(p.getId(), presupuestoId));
        presupuestoRepository.delete(presupuesto);
    }

    public SolicitudDesembolsoDto addBudgetRequest(Long projectId, Long presupuestoId, String email, SolicitudDesembolsoRequest request) {
        Presupuesto presupuesto = getPresupuestoForProject(projectId, presupuestoId, email);
        SolicitudDesembolso solicitud = new SolicitudDesembolso();
        solicitud.setPresupuesto(presupuesto);
        solicitud.setDocumento(request.documento());
        solicitud.setEstado(request.estado());
        solicitudDesembolsoRepository.save(solicitud);
        presupuesto.getSolicitudes().add(solicitud);
        return new SolicitudDesembolsoDto(solicitud.getId(), solicitud.getDocumento(), solicitud.getEstado());
    }

    public SolicitudDesembolsoDto updateBudgetRequest(Long projectId, Long presupuestoId, Long solicitudId, String email, SolicitudDesembolsoRequest request) {
        Presupuesto presupuesto = getPresupuestoForProject(projectId, presupuestoId, email);
        SolicitudDesembolso solicitud = solicitudDesembolsoRepository.findById(solicitudId)
                .orElseThrow(() -> new NotFoundException("Solicitud de desembolso no encontrada"));
        if (!Objects.equals(solicitud.getPresupuesto().getId(), presupuesto.getId())) {
            throw new NotFoundException("La solicitud no pertenece al presupuesto");
        }
        solicitud.setDocumento(request.documento());
        solicitud.setEstado(request.estado());
        return new SolicitudDesembolsoDto(solicitud.getId(), solicitud.getDocumento(), solicitud.getEstado());
    }

    public void deleteBudgetRequest(Long projectId, Long presupuestoId, Long solicitudId, String email) {
        Presupuesto presupuesto = getPresupuestoForProject(projectId, presupuestoId, email);
        SolicitudDesembolso solicitud = solicitudDesembolsoRepository.findById(solicitudId)
                .orElseThrow(() -> new NotFoundException("Solicitud de desembolso no encontrada"));
        if (!Objects.equals(solicitud.getPresupuesto().getId(), presupuesto.getId())) {
            throw new NotFoundException("La solicitud no pertenece al presupuesto");
        }
        presupuesto.getSolicitudes().removeIf(s -> Objects.equals(s.getId(), solicitudId));
        solicitudDesembolsoRepository.delete(solicitud);
    }

    public PresupuestoDto getBudgetDetail(Long projectId, Long presupuestoId, String email) {
        Presupuesto presupuesto = getPresupuestoForProject(projectId, presupuestoId, email);
        return OngMapper.toPresupuestoDto(presupuesto);
    }

    public ObjetivoEspecificoDto getObjectiveDetail(Long projectId, Long objectiveId, String email) {
        ObjetivoEspecifico objetivo = getObjectiveForProject(projectId, objectiveId, email);
        return OngMapper.toObjectiveDto(objetivo);
    }

    public ActivityDto getActivityDetail(Long projectId, Long objectiveId, Long activityId, String email) {
        Actividad actividad = getActivityForObjective(projectId, objectiveId, activityId, email);
        return OngMapper.toActivityDto(actividad);
    }

    private Ong getOngByEmail(String email) {
        return ongRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("No se encontró la ONG asociada al usuario"));
    }

    private Proyecto getProjectForOng(Long projectId, String email) {
        Ong ong = getOngByEmail(email);
        Proyecto proyecto = proyectoRepository.findDetailedById(projectId)
                .orElseThrow(() -> new NotFoundException("Proyecto no encontrado"));
        boolean pertenece = proyecto.getOngs().stream().anyMatch(o -> Objects.equals(o.getId(), ong.getId()));
        if (!pertenece) {
            throw new NotFoundException("El proyecto no pertenece a la ONG autenticada");
        }
        return proyecto;
    }

    private ObjetivoEspecifico getObjectiveForProject(Long projectId, Long objectiveId, String email) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        return proyecto.getObjetivosEspecificos().stream()
                .filter(o -> Objects.equals(o.getId(), objectiveId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Objetivo específico no encontrado"));
    }

    private Actividad getActivityForObjective(Long projectId, Long objectiveId, Long activityId, String email) {
        ObjetivoEspecifico objetivo = getObjectiveForProject(projectId, objectiveId, email);
        return objetivo.getActividades().stream()
                .filter(a -> Objects.equals(a.getId(), activityId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Actividad no encontrada"));
    }

    private Presupuesto getPresupuestoForProject(Long projectId, Long presupuestoId, String email) {
        Proyecto proyecto = getProjectForOng(projectId, email);
        return proyecto.getPresupuestos().stream()
                .filter(p -> Objects.equals(p.getId(), presupuestoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Partida de presupuesto no encontrada"));
    }

    private void applyProjectRequest(Proyecto proyecto, ProjectRequest request) {
        proyecto.setTitulo(request.titulo());
        proyecto.setObjetivoGeneral(request.objetivoGeneral());
        proyecto.setAlcance(request.alcance());
        proyecto.setMontoTotal(request.montoTotal());
        proyecto.setPlazoTotal(request.plazoTotal());
        proyecto.setFechaInicio(request.fechaInicio());
        proyecto.setFechaFin(request.fechaFin());
        proyecto.setEjes(request.ejes());
        proyecto.setResultado(request.resultado());
    }

    private void applyActividadRequest(Actividad actividad, ActividadRequest request) {
        actividad.setNombre(request.nombre());
        actividad.setDescripcion(request.descripcion());
        actividad.setResultadoEsperado(request.resultadoEsperado());
        actividad.setResultadoObtenido(request.resultadoObtenido());
        actividad.setCategoria(request.categoria());
    }

    private void applyPresupuestoRequest(Presupuesto presupuesto, PresupuestoRequest request) {
        presupuesto.setCategoria(request.categoria());
        presupuesto.setCantidad(request.cantidad());
        presupuesto.setUnidades(request.unidades());
        presupuesto.setValor(request.valor());
    }
}
