package com.openfav.backend.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "SOLICUTUD_DESEMBOLSO")
public class SolicitudDesembolso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DESCM")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PRES")
    private Presupuesto presupuesto;

    @Column(name = "DOCUMENTO_DESCM")
    private String documento;

    @Column(name = "ESTADO_DESCM")
    private String estado;
}
