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
    private Integer idDescm;

    @Column(name = "DOCUMENTO_DESCM")
    private String documentoDescm;

    @Column(name = "ESTADO_DESCM")
    private String estadoDescm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRES")
    private Presupuesto presupuesto;
}
