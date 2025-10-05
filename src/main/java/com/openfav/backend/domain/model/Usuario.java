package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "EMAIL_USER", nullable = false, unique = true)
    private String email;

    @Column(name = "CONTRASENIA_USER", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_USER", nullable = false)
    private RoleType role;
}
