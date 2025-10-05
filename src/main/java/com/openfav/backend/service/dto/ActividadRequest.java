package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record ActividadRequest(
        @NotBlank String nombre,
        String descripcion,
        BigDecimal resultadoEsperado,
        BigDecimal resultadoObtenido,
        String categoria
) {
}
