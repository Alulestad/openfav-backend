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
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actividades")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion", length = 1000)
    private String descripcion;

    @Column(name = "resultado_esperado")
    private BigDecimal resultadoEsperado;

    @Column(name = "resultado_obtenido")
    private BigDecimal resultadoObtenido;

    @Column(name = "categoria")
    private String categoria;

    @JsonBackReference(value = "objetivo-actividades")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "objetivo_id")
    private ObjetivoEspecifico objetivo;

    @JsonManagedReference(value = "actividad-fechas")
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ActividadFecha> fechas = new ArrayList<>();

    @JsonManagedReference(value = "actividad-kpis")
    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kpi> kpis = new ArrayList<>();

    public void addFecha(ActividadFecha fecha) {
        fechas.add(fecha);
        fecha.setActividad(this);
    }

    public void addKpi(Kpi kpi) {
        kpis.add(kpi);
        kpi.setActividad(this);
    }

    public void removeFecha(ActividadFecha fecha) {
        fechas.remove(fecha);
        fecha.setActividad(null);
    }

    public void removeKpi(Kpi kpi) {
        kpis.remove(kpi);
        kpi.setActividad(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ObjetivoEspecifico getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(ObjetivoEspecifico objetivo) {
        this.objetivo = objetivo;
    }

    public List<ActividadFecha> getFechas() {
        return fechas;
    }

    public void setFechas(List<ActividadFecha> fechas) {
        this.fechas = fechas;
    }

    public List<Kpi> getKpis() {
        return kpis;
    }

    public void setKpis(List<Kpi> kpis) {
        this.kpis = kpis;
    }
}
