package com.openfav.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openfav.dto.LoginRequest;
import com.openfav.dto.LoginResponse;
import com.openfav.model.Ong;
import com.openfav.model.Usuario;
import com.openfav.repository.OngRepository;
import com.openfav.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final OngRepository ongRepository;

    public AuthService(UsuarioRepository usuarioRepository, OngRepository ongRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ongRepository = ongRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponse authenticate(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndContrasenia(
                request.getEmail(), request.getPassword());

        if (usuarioOpt.isEmpty()) {
            return new LoginResponse(false, null, null, null, "Credenciales inválidas");
        }

        Usuario usuario = usuarioOpt.get();
        Integer ongId = null;

        if ("ONG".equalsIgnoreCase(usuario.getRol())) {
            ongId = ongRepository.findByEmail(usuario.getEmail())
                    .map(Ong::getId)
                    .orElse(null);
        }

        return new LoginResponse(true, usuario.getId(), usuario.getRol(), ongId, "Autenticación exitosa");
    }
}
