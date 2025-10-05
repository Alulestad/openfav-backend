package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ACTIVIDAD")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {
    @Id
    @Column(name = "ID_ACTI")
    private Integer idActi;

    @Column(name = "NOMBRE_ACTI")
    private String nombreActi;

    @Column(name = "DESCRIPCION_ACTI")
    private String descripcionActi;

    @Column(name = "RESULTADO_ESPERADO_ACTI")
    private BigDecimal resultadoEsperadoActi;

    @Column(name = "RESULTADO_OBTENIDO")
    private BigDecimal resultadoObtenido;

    @Column(name = "CATEGORIA")
    private String categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_OBJESP")
    private ObjetivosEspecificos objetivoEspecifico;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Fechas> fechas;

    @OneToMany(mappedBy = "actividad", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Kpi> kpis;
}
