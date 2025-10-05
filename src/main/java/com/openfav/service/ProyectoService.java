package com.openfav.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.dto.ProyectoDetalleDto.ActividadDetalleDto;
import com.openfav.dto.ProyectoDetalleDto.ActividadFechaDto;
import com.openfav.dto.ProyectoDetalleDto.KpiDto;
import com.openfav.dto.ProyectoDetalleDto.ObjetivoEspecificoDetalleDto;
import com.openfav.dto.ProyectoDetalleDto.PresupuestoDto;
import com.openfav.dto.ProyectoDetalleDto.SolicitudDesembolsoDto;
import com.openfav.dto.ProyectoRequest;
import com.openfav.model.Actividad;
import com.openfav.model.ActividadFecha;
import com.openfav.model.Kpi;
import com.openfav.model.ObjetivoEspecifico;
import com.openfav.model.Ong;
import com.openfav.model.OngProyecto;
import com.openfav.model.Presupuesto;
import com.openfav.model.Proyecto;
import com.openfav.model.SolicitudDesembolso;
import com.openfav.repository.ActividadFechaRepository;
import com.openfav.repository.ActividadRepository;
import com.openfav.repository.KpiRepository;
import com.openfav.repository.ObjetivoEspecificoRepository;
import com.openfav.repository.OngProyectoRepository;
import com.openfav.repository.OngRepository;
import com.openfav.repository.PresupuestoRepository;
import com.openfav.repository.ProyectoRepository;
import com.openfav.repository.SolicitudDesembolsoRepository;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final OngRepository ongRepository;
    private final OngProyectoRepository ongProyectoRepository;
    private final ObjetivoEspecificoRepository objetivoEspecificoRepository;
    private final ActividadRepository actividadRepository;
    private final ActividadFechaRepository actividadFechaRepository;
    private final KpiRepository kpiRepository;
    private final PresupuestoRepository presupuestoRepository;
    private final SolicitudDesembolsoRepository solicitudRepository;

    public ProyectoService(ProyectoRepository proyectoRepository,
            OngRepository ongRepository,
            OngProyectoRepository ongProyectoRepository,
            ObjetivoEspecificoRepository objetivoEspecificoRepository,
            ActividadRepository actividadRepository,
            ActividadFechaRepository actividadFechaRepository,
            KpiRepository kpiRepository,
            PresupuestoRepository presupuestoRepository,
            SolicitudDesembolsoRepository solicitudRepository) {
        this.proyectoRepository = proyectoRepository;
        this.ongRepository = ongRepository;
        this.ongProyectoRepository = ongProyectoRepository;
        this.objetivoEspecificoRepository = objetivoEspecificoRepository;
        this.actividadRepository = actividadRepository;
        this.actividadFechaRepository = actividadFechaRepository;
        this.kpiRepository = kpiRepository;
        this.presupuestoRepository = presupuestoRepository;
        this.solicitudRepository = solicitudRepository;
    }

    @Transactional(readOnly = true)
    public ProyectoDetalleDto obtenerDetalle(Integer proyectoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));

        ProyectoDetalleDto dto = new ProyectoDetalleDto();
        dto.setId(proyecto.getId());
        dto.setTitulo(proyecto.getTitulo());
        dto.setObjetivoGeneral(proyecto.getObjetivoGeneral());
        dto.setAlcance(proyecto.getAlcance());
        dto.setMontoTotal(proyecto.getMontoTotal());
        dto.setPlazoTotal(proyecto.getPlazoTotal());
        dto.setFechaInicio(proyecto.getFechaInicio());
        dto.setFechaFin(proyecto.getFechaFin());
        dto.setEjes(proyecto.getEjes());
        dto.setResultado(proyecto.getResultado());

        List<ObjetivoEspecifico> objetivos = objetivoEspecificoRepository.findByProyectoId(proyectoId);
        List<ObjetivoEspecificoDetalleDto> objetivosDto = objetivos.stream()
                .map(this::mapObjetivo)
                .collect(Collectors.toList());
        dto.setObjetivos(objetivosDto);

        List<Presupuesto> presupuestos = presupuestoRepository.findByProyectoId(proyectoId);
        dto.setPresupuestos(presupuestos.stream()
                .map(this::mapPresupuesto)
                .collect(Collectors.toList()));

        List<SolicitudDesembolso> solicitudes = solicitudRepository.findByProyectoId(proyectoId);
        dto.setSolicitudes(solicitudes.stream()
                .map(this::mapSolicitud)
                .collect(Collectors.toList()));

        return dto;
    }

    @Transactional
    public Proyecto crearProyecto(Integer ongId, ProyectoRequest request) {
        Ong ong = ongRepository.findById(ongId)
                .orElseThrow(() -> new IllegalArgumentException("ONG no encontrada"));

        Proyecto proyecto = new Proyecto();
        actualizarEntidadProyecto(request, proyecto);
        Proyecto guardado = proyectoRepository.save(proyecto);

        OngProyecto relacion = new OngProyecto(ong, guardado);
        ongProyectoRepository.save(relacion);

        return guardado;
    }

    @Transactional
    public Proyecto actualizarProyecto(Integer proyectoId, ProyectoRequest request) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));
        actualizarEntidadProyecto(request, proyecto);
        return proyectoRepository.save(proyecto);
    }

    @Transactional
    public void eliminarProyecto(Integer proyectoId) {
        Proyecto proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new IllegalArgumentException("Proyecto no encontrado"));

        // Eliminar datos dependientes en cascada manual
        List<ObjetivoEspecifico> objetivos = objetivoEspecificoRepository.findByProyectoId(proyectoId);
        for (ObjetivoEspecifico objetivo : objetivos) {
            List<Actividad> actividades = actividadRepository.findByObjetivoEspecificoId(objetivo.getId());
            for (Actividad actividad : actividades) {
                actividadFechaRepository.deleteAll(actividadFechaRepository.findByActividadId(actividad.getId()));
                kpiRepository.deleteAll(kpiRepository.findByActividadId(actividad.getId()));
            }
            actividadRepository.deleteAll(actividades);
        }
        objetivoEspecificoRepository.deleteAll(objetivos);

        List<Presupuesto> presupuestos = presupuestoRepository.findByProyectoId(proyectoId);
        for (Presupuesto presupuesto : presupuestos) {
            solicitudRepository.deleteAll(solicitudRepository.findByPresupuestoId(presupuesto.getId()));
        }
        presupuestoRepository.deleteAll(presupuestos);

        ongProyectoRepository.deleteByIdProyectoId(proyectoId);
        proyectoRepository.delete(proyecto);
    }

    private void actualizarEntidadProyecto(ProyectoRequest request, Proyecto proyecto) {
        proyecto.setTitulo(request.getTitulo());
        proyecto.setObjetivoGeneral(request.getObjetivoGeneral());
        proyecto.setAlcance(request.getAlcance());
        proyecto.setMontoTotal(request.getMontoTotal());
        proyecto.setPlazoTotal(request.getPlazoTotal());
        proyecto.setFechaInicio(request.getFechaInicio());
        proyecto.setFechaFin(request.getFechaFin());
        proyecto.setEjes(request.getEjes());
        proyecto.setResultado(request.getResultado());
    }

    private ObjetivoEspecificoDetalleDto mapObjetivo(ObjetivoEspecifico objetivo) {
        ObjetivoEspecificoDetalleDto dto = new ObjetivoEspecificoDetalleDto();
        dto.setId(objetivo.getId());
        dto.setObjetivo(objetivo.getObjetivo());
        dto.setEjes(objetivo.getEjes());

        List<Actividad> actividades = actividadRepository.findByObjetivoEspecificoId(objetivo.getId());
        List<ActividadDetalleDto> actividadesDto = actividades.stream()
                .map(this::mapActividad)
                .collect(Collectors.toList());
        dto.setActividades(actividadesDto);
        return dto;
    }

    private ActividadDetalleDto mapActividad(Actividad actividad) {
        ActividadDetalleDto dto = new ActividadDetalleDto();
        dto.setId(actividad.getId());
        dto.setNombre(actividad.getNombre());
        dto.setDescripcion(actividad.getDescripcion());
        dto.setResultadoEsperado(actividad.getResultadoEsperado());
        dto.setResultadoObtenido(actividad.getResultadoObtenido());
        dto.setCategoria(actividad.getCategoria());

        List<ActividadFecha> fechas = actividadFechaRepository.findByActividadId(actividad.getId());
        dto.setFechas(fechas.stream().map(this::mapFecha).collect(Collectors.toList()));

        List<Kpi> kpis = kpiRepository.findByActividadId(actividad.getId());
        dto.setKpis(kpis.stream().map(this::mapKpi).collect(Collectors.toList()));

        return dto;
    }

    private ActividadFechaDto mapFecha(ActividadFecha fecha) {
        ActividadFechaDto dto = new ActividadFechaDto();
        dto.setId(fecha.getId());
        dto.setFechaInicio(fecha.getFechaInicio());
        dto.setFechaFin(fecha.getFechaFin());
        return dto;
    }

    private KpiDto mapKpi(Kpi kpi) {
        KpiDto dto = new KpiDto();
        dto.setId(kpi.getId());
        dto.setValor(kpi.getValor());
        dto.setDescripcion(kpi.getDescripcion());
        return dto;
    }

    private PresupuestoDto mapPresupuesto(Presupuesto presupuesto) {
        PresupuestoDto dto = new PresupuestoDto();
        dto.setId(presupuesto.getId());
        dto.setCategoria(presupuesto.getCategoria());
        dto.setCantidad(presupuesto.getCantidad());
        dto.setUnidades(presupuesto.getUnidades());
        dto.setValor(presupuesto.getValor());
        return dto;
    }

    private SolicitudDesembolsoDto mapSolicitud(SolicitudDesembolso solicitud) {
        SolicitudDesembolsoDto dto = new SolicitudDesembolsoDto();
        dto.setId(solicitud.getId());
        dto.setPresupuestoId(solicitud.getPresupuesto() != null ? solicitud.getPresupuesto().getId() : null);
        dto.setDocumento(solicitud.getDocumento());
        dto.setEstado(solicitud.getEstado());
        return dto;
    }
}
