package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PresupuestoRequest(
        @NotBlank String categoria,
        @NotNull BigDecimal cantidad,
        @NotNull BigDecimal unidades,
        @NotNull BigDecimal valor
) {
}
