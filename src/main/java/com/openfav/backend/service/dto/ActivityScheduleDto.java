package com.openfav.backend.service.dto;

import java.time.LocalDate;

public record ActivityScheduleDto(
        Long id,
        LocalDate fechaInicio,
        LocalDate fechaFin
) {
}
