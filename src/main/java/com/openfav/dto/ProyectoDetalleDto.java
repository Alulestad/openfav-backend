package com.openfav.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.math.BigDecimal;

public class ProyectoDetalleDto {
    private Integer id;
    private String titulo;
    private String objetivoGeneral;
    private Integer alcance;
    private String montoTotal;
    private Integer plazoTotal;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String ejes;
    private String resultado;
    private List<ObjetivoEspecificoDetalleDto> objetivos = new ArrayList<>();
    private List<PresupuestoDto> presupuestos = new ArrayList<>();
    private List<SolicitudDesembolsoDto> solicitudes = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public Integer getAlcance() {
        return alcance;
    }

    public void setAlcance(Integer alcance) {
        this.alcance = alcance;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Integer getPlazoTotal() {
        return plazoTotal;
    }

    public void setPlazoTotal(Integer plazoTotal) {
        this.plazoTotal = plazoTotal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEjes() {
        return ejes;
    }

    public void setEjes(String ejes) {
        this.ejes = ejes;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public List<ObjetivoEspecificoDetalleDto> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoEspecificoDetalleDto> objetivos) {
        this.objetivos = objetivos;
    }

    public List<PresupuestoDto> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<PresupuestoDto> presupuestos) {
        this.presupuestos = presupuestos;
    }

    public List<SolicitudDesembolsoDto> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudDesembolsoDto> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public static class ObjetivoEspecificoDetalleDto {
        private Integer id;
        private String objetivo;
        private String ejes;
        private List<ActividadDetalleDto> actividades = new ArrayList<>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getObjetivo() {
            return objetivo;
        }

        public void setObjetivo(String objetivo) {
            this.objetivo = objetivo;
        }

        public String getEjes() {
            return ejes;
        }

        public void setEjes(String ejes) {
            this.ejes = ejes;
        }

        public List<ActividadDetalleDto> getActividades() {
            return actividades;
        }

        public void setActividades(List<ActividadDetalleDto> actividades) {
            this.actividades = actividades;
        }
    }

    public static class ActividadDetalleDto {
        private Integer id;
        private String nombre;
        private String descripcion;
        private BigDecimal resultadoEsperado;
        private BigDecimal resultadoObtenido;
        private String categoria;
        private List<ActividadFechaDto> fechas = new ArrayList<>();
        private List<KpiDto> kpis = new ArrayList<>();

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public BigDecimal getResultadoEsperado() {
            return resultadoEsperado;
        }

        public void setResultadoEsperado(BigDecimal resultadoEsperado) {
            this.resultadoEsperado = resultadoEsperado;
        }

        public BigDecimal getResultadoObtenido() {
            return resultadoObtenido;
        }

        public void setResultadoObtenido(BigDecimal resultadoObtenido) {
            this.resultadoObtenido = resultadoObtenido;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public List<ActividadFechaDto> getFechas() {
            return fechas;
        }

        public void setFechas(List<ActividadFechaDto> fechas) {
            this.fechas = fechas;
        }

        public List<KpiDto> getKpis() {
            return kpis;
        }

        public void setKpis(List<KpiDto> kpis) {
            this.kpis = kpis;
        }
    }

    public static class ActividadFechaDto {
        private Integer id;
        private LocalDate fechaInicio;
        private LocalDate fechaFin;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public LocalDate getFechaInicio() {
            return fechaInicio;
        }

        public void setFechaInicio(LocalDate fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public LocalDate getFechaFin() {
            return fechaFin;
        }

        public void setFechaFin(LocalDate fechaFin) {
            this.fechaFin = fechaFin;
        }
    }

    public static class KpiDto {
        private Integer id;
        private String valor;
        private String descripcion;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }
    }

    public static class PresupuestoDto {
        private Integer id;
        private String categoria;
        private BigDecimal cantidad;
        private BigDecimal unidades;
        private BigDecimal valor;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getCategoria() {
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public BigDecimal getCantidad() {
            return cantidad;
        }

        public void setCantidad(BigDecimal cantidad) {
            this.cantidad = cantidad;
        }

        public BigDecimal getUnidades() {
            return unidades;
        }

        public void setUnidades(BigDecimal unidades) {
            this.unidades = unidades;
        }

        public BigDecimal getValor() {
            return valor;
        }

        public void setValor(BigDecimal valor) {
            this.valor = valor;
        }
    }

    public static class SolicitudDesembolsoDto {
        private Integer id;
        private Integer presupuestoId;
        private String documento;
        private String estado;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getPresupuestoId() {
            return presupuestoId;
        }

        public void setPresupuestoId(Integer presupuestoId) {
            this.presupuestoId = presupuestoId;
        }

        public String getDocumento() {
            return documento;
        }

        public void setDocumento(String documento) {
            this.documento = documento;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }
}
