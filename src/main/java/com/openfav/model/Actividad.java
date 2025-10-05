package com.openfav.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTI")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OBJESP")
    private ObjetivoEspecifico objetivoEspecifico;

    @Column(name = "NOMBRE_ACTI")
    private String nombre;

    @Column(name = "DESCRIPCION_ACTI")
    private String descripcion;

    @Column(name = "RESULTADO_ESPERADO_ACTI", precision = 19, scale = 2)
    private BigDecimal resultadoEsperado;

    @Column(name = "RESULTADO_OBTENIDO", precision = 19, scale = 2)
    private BigDecimal resultadoObtenido;

    @Column(name = "CATEGORIA")
    private String categoria;

    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY)
    private List<ActividadFecha> fechas = new ArrayList<>();

    @OneToMany(mappedBy = "actividad", fetch = FetchType.LAZY)
    private List<Kpi> kpis = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ObjetivoEspecifico getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(ObjetivoEspecifico objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
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
