package com.prismatech.prismaproject.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PresupuestoDto {
    private Integer idPres;
    private Integer idProy;
    private String categoriaPres;
    private BigDecimal cantidadPres;
    private BigDecimal unidadesPres;
    private BigDecimal valorPres;
}
