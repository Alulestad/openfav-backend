package com.prismatech.prismaproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Integer id;
    private String email;
    private String role;
}
