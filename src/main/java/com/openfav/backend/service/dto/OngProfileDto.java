package com.openfav.backend.service.dto;

public record OngProfileDto(
        Long id,
        String nombre,
        String representanteLegal,
        String email,
        String celular,
        String direccion
) {
}
