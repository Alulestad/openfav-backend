package com.prismatech.prismaproject.service.impl;

import com.prismatech.prismaproject.dto.LoginRequest;
import com.prismatech.prismaproject.dto.LoginResponse;
import com.prismatech.prismaproject.model.Usuario;
import com.prismatech.prismaproject.repository.UsuarioRepository;
import com.prismatech.prismaproject.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        if (request.getEmail() == null || request.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and password are required");
        }

        Usuario usuario = usuarioRepository.findByEmailUser(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"));

        if (!usuario.getContraseniaUser().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales incorrectas");
        }

        return new LoginResponse(usuario.getIdUser(), usuario.getEmailUser(), usuario.getRolUser());
    }
}
