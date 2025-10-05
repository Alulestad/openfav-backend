package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SOLICUTUD_DESEMBOLSO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDesembolso {
    @Id
    @Column(name = "ID_DESCM")
    @SequenceGenerator(name = "descm_seq", sequenceName = "seq_descm_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "descm_seq")
    private Integer idDescm;

    @Column(name = "DOCUMENTO_DESCM")
    private String documentoDescm;

    @Column(name = "ESTADO_DESCM")
    private String estadoDescm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRES")
    private Presupuesto presupuesto;
}
