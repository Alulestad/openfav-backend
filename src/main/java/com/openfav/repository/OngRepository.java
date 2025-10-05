package com.openfav.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openfav.model.Ong;

public interface OngRepository extends JpaRepository<Ong, Integer> {
    Optional<Ong> findByEmail(String email);
}
