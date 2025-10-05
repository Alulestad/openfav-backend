package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ONG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ong {
    @Id
    @Column(name = "ID_ONG")
    private Integer idOng;

    @Column(name = "NOMBRE_ONG")
    private String nombreOng;

    @Column(name = "REPRESENTANTE_LEGAL_ONG")
    private String representanteLegalOng;

    @Column(name = "EMAIL_ONG")
    private String emailOng;

    @Column(name = "CELULAR_ONG")
    private String celularOng;

    @Column(name = "DIRECCION_ONG")
    private String direccionOng;

    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, orphanRemoval = true)
    private java.util.List<Relacion> relaciones;
}
