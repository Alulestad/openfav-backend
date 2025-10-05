package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRESUPUESTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Presupuesto {
    @Id
    @Column(name = "ID_PRES")
    @SequenceGenerator(name = "pres_seq", sequenceName = "seq_pres_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pres_seq")
    private Integer idPres;

    @Column(name = "CATEGORIA_PRES")
    private String categoriaPres;

    @Column(name = "CANTIDAD_PRES")
    private BigDecimal cantidadPres;

    @Column(name = "UNIDADES_PRES")
    private BigDecimal unidadesPres;

    @Column(name = "VALOR_PRES")
    private BigDecimal valorPres;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PROY")
    private Proyecto proyecto;
}
