package com.openfav.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailAndContrasenia(String email, String contrasenia);
}
