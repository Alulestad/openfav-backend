package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "OBJETIVOS_ESPECIFICOS")
public class ObjetivoEspecifico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OBJESP")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROY")
    private Proyecto proyecto;

    @Column(name = "OBJETIVO_OBJESP")
    private String objetivo;

    @Column(name = "EJES_OBJESP")
    private String ejes;

    @OneToMany(mappedBy = "objetivoEspecifico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Actividad> actividades = new ArrayList<>();
}
