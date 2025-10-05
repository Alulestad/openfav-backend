package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.service.dto.LoginRequest;
import com.senadi.pasantes.intranet.service.dto.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
