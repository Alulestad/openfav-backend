package com.senadi.pasantes.intranet.service.impl;

import com.senadi.pasantes.intranet.model.Usuario;
import com.senadi.pasantes.intranet.repository.UsuarioRepository;
import com.senadi.pasantes.intranet.service.AuthService;
import com.senadi.pasantes.intranet.service.dto.LoginRequest;
import com.senadi.pasantes.intranet.service.dto.LoginResponse;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return usuarioRepository.findByEmail(request.getEmail())
                .filter(usuario -> usuario.getPassword().equals(request.getPassword()))
                .map(usuario -> new LoginResponse(true, usuario.getRole(), usuario.getId()))
                .orElse(new LoginResponse(false, null, null));
    }
}
