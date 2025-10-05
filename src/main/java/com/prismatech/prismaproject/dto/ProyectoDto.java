package com.prismatech.prismaproject.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProyectoDto {
    private Integer idProy;
    private Integer idOng;
    private String tituloProy;
    private String objetivoGeneralProy;
    private Integer alcanceProy;
    private String montoTotalProy;
    private Integer plazoTotalProy;
    private LocalDate fechaInicioProy;
    private LocalDate fechaFinProy;
    private String ejesProy;
    private String resultadoProy;
}
