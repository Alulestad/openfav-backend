package com.openfav.backend.service.dto;

import com.openfav.backend.domain.model.RoleType;

public record LoginResponse(
        String token,
        RoleType role
) {
}
