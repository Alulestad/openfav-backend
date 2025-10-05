package com.openfav.backend.service.dto;

import java.math.BigDecimal;
import java.util.List;

public record PresupuestoDto(
        Long id,
        String categoria,
        BigDecimal cantidad,
        BigDecimal unidades,
        BigDecimal valor,
        List<SolicitudDesembolsoDto> solicitudes
) {
}
