package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotBlank;

public record SolicitudDesembolsoRequest(
        @NotBlank String documento,
        @NotBlank String estado
) {
}
