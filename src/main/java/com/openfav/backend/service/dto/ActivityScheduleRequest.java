package com.openfav.backend.service.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ActivityScheduleRequest(
        @NotNull LocalDate fechaInicio,
        @NotNull LocalDate fechaFin
) {
}
