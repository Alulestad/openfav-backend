package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PROYECTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {
    @Id
    @Column(name = "ID_PROY")
    @SequenceGenerator(name = "proy_seq", sequenceName = "seq_proy_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proy_seq")
    private Integer idProy;

    @Column(name = "TITULO_PROY")
    private String tituloProy;

    @Column(name = "OBJETIVO_GENERAL_PROY")
    private String objetivoGeneralProy;

    @Column(name = "ALCANCE_PROY")
    private Integer alcanceProy;

    @Column(name = "MONTO_TOTAL_PROY")
    private String montoTotalProy;

    @Column(name = "PLAZO_TOTAL_PROY")
    private Integer plazoTotalProy;

    @Column(name = "FECHA_INICIO_PROY")
    private LocalDate fechaInicioProy;

    @Column(name = "FECHA_FIN_PROY")
    private LocalDate fechaFinProy;

    @Column(name = "EJES_PROY")
    private String ejesProy;

    @Column(name = "RESULTADO_PROY")
    private String resultadoProy;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ObjetivosEspecificos> objetivosEspecificos;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Presupuesto> presupuestos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ONG")
    private Ong ong;
}
