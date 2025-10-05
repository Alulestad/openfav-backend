package com.senadi.pasantes.intranet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proyectos")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "objetivo_general", length = 1000)
    private String objetivoGeneral;

    @Column(name = "alcance")
    private Integer alcance;

    @Column(name = "monto_total")
    private BigDecimal montoTotal;

    @Column(name = "plazo_total")
    private Integer plazoTotal;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Column(name = "ejes", length = 1000)
    private String ejes;

    @Column(name = "resultado", length = 1000)
    private String resultado;

    @JsonBackReference(value = "ong-proyecto")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ong_id")
    private Ong ong;

    @JsonManagedReference(value = "proyecto-objetivos")
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjetivoEspecifico> objetivosEspecificos = new ArrayList<>();

    @JsonManagedReference(value = "proyecto-presupuestos")
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PresupuestoItem> presupuesto = new ArrayList<>();

    @JsonManagedReference(value = "proyecto-solicitudes")
    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudDesembolso> solicitudes = new ArrayList<>();

    public void addObjetivo(ObjetivoEspecifico objetivo) {
        objetivosEspecificos.add(objetivo);
        objetivo.setProyecto(this);
    }

    public void addPresupuesto(PresupuestoItem item) {
        presupuesto.add(item);
        item.setProyecto(this);
    }

    public void addSolicitud(SolicitudDesembolso solicitud) {
        solicitudes.add(solicitud);
        solicitud.setProyecto(this);
    }

    public void removeObjetivo(ObjetivoEspecifico objetivo) {
        objetivosEspecificos.remove(objetivo);
        objetivo.setProyecto(null);
    }

    public void removePresupuesto(PresupuestoItem item) {
        presupuesto.remove(item);
        item.setProyecto(null);
    }

    public void removeSolicitud(SolicitudDesembolso solicitud) {
        solicitudes.remove(solicitud);
        solicitud.setProyecto(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
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

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public List<ObjetivoEspecifico> getObjetivosEspecificos() {
        return objetivosEspecificos;
    }

    public void setObjetivosEspecificos(List<ObjetivoEspecifico> objetivosEspecificos) {
        this.objetivosEspecificos = objetivosEspecificos;
    }

    public List<PresupuestoItem> getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(List<PresupuestoItem> presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<SolicitudDesembolso> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudDesembolso> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
