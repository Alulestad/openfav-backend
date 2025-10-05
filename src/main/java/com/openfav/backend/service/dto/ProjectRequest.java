package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ProjectRequest(
        @NotBlank String titulo,
        @NotBlank String objetivoGeneral,
        Integer alcance,
        String montoTotal,
        Integer plazoTotal,
        @NotNull LocalDate fechaInicio,
        @NotNull LocalDate fechaFin,
        String ejes,
        String resultado
) {
}
