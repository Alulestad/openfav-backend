package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ACTIVIDAD")
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ACTI")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OBJESP")
    private ObjetivoEspecifico objetivoEspecifico;

    @Column(name = "NOMBRE_ACTI")
    private String nombre;

    @Column(name = "DESCRIPCION_ACTI")
    private String descripcion;

    @Column(name = "RESULTADO_ESPERADO_ACTI")
    private BigDecimal resultadoEsperado;

    @Column(name = "RESULTADO_OBTENIDO")
    private BigDecimal resultadoObtenido;

    @Column(name = "CATEGORIA")
    private String categoria;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FechaActividad> fechas = new ArrayList<>();

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kpi> kpis = new ArrayList<>();
}
