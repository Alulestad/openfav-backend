package com.prismatech.prismaproject.service;

import com.prismatech.prismaproject.dto.LoginRequest;
import com.prismatech.prismaproject.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
