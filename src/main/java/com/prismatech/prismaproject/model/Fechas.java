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
    private Integer idFech;

    @Column(name = "FECHA_INICIO_FECH")
    private LocalDate fechaInicioFech;

    @Column(name = "FECHA_FIN_FECH")
    private LocalDate fechaFinFech;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACTI")
    private Actividad actividad;
}
