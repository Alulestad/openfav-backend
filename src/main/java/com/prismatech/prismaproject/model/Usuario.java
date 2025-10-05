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
    private Integer idUser;

    @Column(name = "EMAIL_USER")
    private String emailUser;

    @Column(name = "CONTRASENIA_USER")
    private String contraseniaUser;

    @Column(name = "ROL_USER")
    private String rolUser;
}
