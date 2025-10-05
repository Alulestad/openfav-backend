package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ONG")
public class Ong {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ONG")
    private Long id;

    @Column(name = "NOMBRE_ONG")
    private String nombre;

    @Column(name = "REPRESENTANTE_LEGAL_ONG")
    private String representanteLegal;

    @Column(name = "EMAIL_ONG")
    private String email;

    @Column(name = "CELULAR_ONG")
    private String celular;

    @Column(name = "DIRECCION_ONG")
    private String direccion;

    @ManyToMany
    @JoinTable(
            name = "RELATIONSHIP_1",
            joinColumns = @JoinColumn(name = "ID_ONG"),
            inverseJoinColumns = @JoinColumn(name = "ID_PROY")
    )
    private Set<Proyecto> proyectos = new HashSet<>();
}
