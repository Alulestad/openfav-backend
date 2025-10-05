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
@Table(name = "PRESUPUESTO")
public class Presupuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRES")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROY")
    private Proyecto proyecto;

    @Column(name = "CATEGORIA_PRES")
    private String categoria;

    @Column(name = "CANTIDAD_PRES")
    private BigDecimal cantidad;

    @Column(name = "UNIDADES_PRES")
    private BigDecimal unidades;

    @Column(name = "VALOR_PRES")
    private BigDecimal valor;

    @OneToMany(mappedBy = "presupuesto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SolicitudDesembolso> solicitudes = new ArrayList<>();
}
