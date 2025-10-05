package com.openfav.backend.service.dto;

public record SolicitudDesembolsoDto(
        Long id,
        String documento,
        String estado
) {
}
