package com.openfav.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "PROYECTO")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROY")
    private Integer id;

    @Column(name = "ID_PRES")
    private Integer presupuestoPrincipalId;

    @Column(name = "TITULO_PROY")
    private String titulo;

    @Column(name = "OBJETIVO_GENERAL_PROY", length = 1000)
    private String objetivoGeneral;

    @Column(name = "ALCANCE_PROY")
    private Integer alcance;

    @Column(name = "MONTO_TOTAL_PROY")
    private String montoTotal;

    @Column(name = "PLAZO_TOTAL_PROY")
    private Integer plazoTotal;

    @Column(name = "FECHA_INICIO_PROY")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN_PROY")
    private LocalDate fechaFin;

    @Column(name = "EJES_PROY", length = 1000)
    private String ejes;

    @Column(name = "RESULTADO_PROY")
    private String resultado;

    @OneToMany(mappedBy = "proyecto", fetch = FetchType.LAZY)
    private List<ObjetivoEspecifico> objetivosEspecificos = new ArrayList<>();

    @OneToMany(mappedBy = "proyecto", fetch = FetchType.LAZY)
    private List<Presupuesto> presupuestos = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPresupuestoPrincipalId() {
        return presupuestoPrincipalId;
    }

    public void setPresupuestoPrincipalId(Integer presupuestoPrincipalId) {
        this.presupuestoPrincipalId = presupuestoPrincipalId;
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

    public List<ObjetivoEspecifico> getObjetivosEspecificos() {
        return objetivosEspecificos;
    }

    public void setObjetivosEspecificos(List<ObjetivoEspecifico> objetivosEspecificos) {
        this.objetivosEspecificos = objetivosEspecificos;
    }

    public List<Presupuesto> getPresupuestos() {
        return presupuestos;
    }

    public void setPresupuestos(List<Presupuesto> presupuestos) {
        this.presupuestos = presupuestos;
    }
}
