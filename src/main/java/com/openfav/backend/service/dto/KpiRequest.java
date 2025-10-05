package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotBlank;

public record KpiRequest(
        @NotBlank String valor,
        String descripcion
) {
}
