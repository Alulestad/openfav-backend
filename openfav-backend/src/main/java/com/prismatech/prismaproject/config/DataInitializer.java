package com.prismatech.prismaproject.config;

import com.prismatech.prismaproject.model.Usuario;
import com.prismatech.prismaproject.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedDefaultUsers(UsuarioRepository usuarioRepository) {
        return args -> {
            createIfMissing(usuarioRepository, "admin", "admin", "ADMIN");
            createIfMissing(usuarioRepository, "ong", "ong", "ONG");
        };
    }

    private void createIfMissing(UsuarioRepository repository, String email, String password, String role) {
        repository.findByEmailUser(email).orElseGet(() -> repository.save(new Usuario(null, email, password, role)));
    }
}
