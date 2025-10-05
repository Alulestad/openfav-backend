package com.openfav.dto;

import java.math.BigDecimal;

public class ActividadRequest {
    private String nombre;
    private String descripcion;
    private BigDecimal resultadoEsperado;
    private BigDecimal resultadoObtenido;
    private String categoria;

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
}
