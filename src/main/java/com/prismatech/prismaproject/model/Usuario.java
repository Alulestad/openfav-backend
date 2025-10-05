package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USUARIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @Column(name = "ID_USER")
    @SequenceGenerator(name = "user_seq", sequenceName = "seq_user_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Integer idUser;

    @Column(name = "EMAIL_USER")
    private String emailUser;

    @Column(name = "CONTRASENIA_USER")
    private String contraseniaUser;

    @Column(name = "ROL_USER")
    private String rolUser;
}
