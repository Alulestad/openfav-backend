package com.prismatech.prismaproject.repository;

import com.prismatech.prismaproject.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmailUser(String emailUser);
}
