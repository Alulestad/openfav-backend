package com.openfav.backend.service.dto;

import java.util.List;

public record ObjetivoEspecificoDto(
        Long id,
        String objetivo,
        String ejes,
        List<ActivityDto> actividades
) {
}
