package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "FECHAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fechas {
    @Id
    @Column(name = "ID_FECH")
    @SequenceGenerator(name = "fechas_seq", sequenceName = "seq_fechas_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fechas_seq")
    private Integer idFech;

    @Column(name = "FECHA_INICIO_FECH")
    private LocalDate fechaInicioFech;

    @Column(name = "FECHA_FIN_FECH")
    private LocalDate fechaFinFech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACTI")
    private Actividad actividad;
}
