package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "FECHAS")
public class FechaActividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FECH")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ACTI")
    private Actividad actividad;

    @Column(name = "FECHA_INICIO_FECH")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN_FECH")
    private LocalDate fechaFin;
}
