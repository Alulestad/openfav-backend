package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotBlank;

public record ObjetivoEspecificoRequest(
        @NotBlank String objetivo,
        String ejes
) {
}
