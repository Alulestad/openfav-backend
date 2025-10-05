package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PROYECTO")
public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROY")
    private Long id;

    @Column(name = "ID_PRES")
    private Long presupuestoPrincipalId;

    @Column(name = "TITULO_PROY")
    private String titulo;

    @Column(name = "OBJETIVO_GENERAL_PROY", length = 1000)
    private String objetivoGeneral;

    @Column(name = "ALCANCE_PROY")
    private Integer alcance;

    @Column(name = "MONTO_TOTAL_PROY")
    private String montoTotal;

    @Column(name = "PLAZO_TOTAL_PROY")
    private Integer plazoTotal;

    @Column(name = "FECHA_INICIO_PROY")
    private LocalDate fechaInicio;

    @Column(name = "FECHA_FIN_PROY")
    private LocalDate fechaFin;

    @Column(name = "EJES_PROY", length = 1000)
    private String ejes;

    @Column(name = "RESULTADO_PROY")
    private String resultado;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjetivoEspecifico> objetivosEspecificos = new ArrayList<>();

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presupuesto> presupuestos = new ArrayList<>();

    @ManyToMany(mappedBy = "proyectos")
    private Set<Ong> ongs = new HashSet<>();
}
