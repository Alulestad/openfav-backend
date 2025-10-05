package com.openfav.backend.service;

import com.openfav.backend.domain.model.Usuario;
import com.openfav.backend.domain.repository.UsuarioRepository;
import com.openfav.backend.security.JwtService;
import com.openfav.backend.service.dto.LoginRequest;
import com.openfav.backend.service.dto.LoginResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthService(AuthenticationManager authenticationManager,
                       UsuarioRepository usuarioRepository,
                       JwtService jwtService,
                       UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
        String token = jwtService.generateToken(userDetails);

        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado tras autenticación"));

        return new LoginResponse(token, usuario.getRole());
    }
}
