package com.openfav.backend.service.dto;

import java.time.LocalDate;

public record ProjectSummaryDto(
        Long id,
        String titulo,
        String descripcionCorta,
        LocalDate fechaInicio,
        LocalDate fechaFin
) {
}
