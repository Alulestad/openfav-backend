package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KPI")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kpi {
    @Id
    @Column(name = "ID_KPI")
    @SequenceGenerator(name = "kpi_seq", sequenceName = "seq_kpi_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "kpi_seq")
    private Integer idKpi;

    @Column(name = "VALOR_KPI")
    private String valorKpi;

    @Column(name = "DESCRIPCION_KPI")
    private String descripcionKpi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACTI")
    private Actividad actividad;
}
