package com.openfav.backend.service.dto;

import java.time.LocalDate;
import java.util.List;

public record ProjectDetailDto(
        Long id,
        String titulo,
        String objetivoGeneral,
        Integer alcance,
        String montoTotal,
        Integer plazoTotal,
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String ejes,
        String resultado,
        List<ObjetivoEspecificoDto> objetivos,
        List<PresupuestoDto> presupuestos
) {
}
