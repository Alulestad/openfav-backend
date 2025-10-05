package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "OBJETIVOS_ESPECIFICOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjetivosEspecificos {
    @Id
    @Column(name = "ID_OBJESP")
    @SequenceGenerator(name = "objesp_seq", sequenceName = "seq_objesp_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "objesp_seq")
    private Integer idObjesp;

    @Column(name = "OBJETIVO_OBJESP")
    private String objetivoObjesp;

    @Column(name = "EJES_OBJESP")
    private String ejesObjesp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROY")
    private Proyecto proyecto;

    @OneToMany(mappedBy = "objetivoEspecifico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Actividad> actividades;
}
