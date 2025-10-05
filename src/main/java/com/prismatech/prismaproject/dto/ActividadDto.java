package com.prismatech.prismaproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ActividadDto {
    private Integer idActi;
    private Integer idObjesp;
    private String nombreActi;
    private String descripcionActi;
    private BigDecimal resultadoEsperadoActi;
    private BigDecimal resultadoObtenido;
    private String categoria;
}
