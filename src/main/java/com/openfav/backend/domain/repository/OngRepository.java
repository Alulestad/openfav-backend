package com.openfav.backend.domain.repository;

import com.openfav.backend.domain.model.Ong;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OngRepository extends JpaRepository<Ong, Long> {

    @EntityGraph(attributePaths = {"proyectos"})
    Optional<Ong> findByEmail(String email);
}
