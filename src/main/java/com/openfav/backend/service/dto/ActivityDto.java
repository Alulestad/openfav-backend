package com.openfav.backend.service.dto;

import java.math.BigDecimal;
import java.util.List;

public record ActivityDto(
        Long id,
        String nombre,
        String descripcion,
        BigDecimal resultadoEsperado,
        BigDecimal resultadoObtenido,
        String categoria,
        List<ActivityScheduleDto> fechas,
        List<KpiDto> kpis
) {
}
