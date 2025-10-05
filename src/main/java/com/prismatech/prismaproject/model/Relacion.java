package com.prismatech.prismaproject.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "RELATIONSHIP_1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relacion {
    @EmbeddedId
    private RelacionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idOng")
    @JoinColumn(name = "ID_ONG")
    private Ong ong;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idProy")
    @JoinColumn(name = "ID_PROY")
    private Proyecto proyecto;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    public static class RelacionId implements java.io.Serializable {
        @Column(name = "ID_ONG")
        private Integer idOng;

        @Column(name = "ID_PROY")
        private Integer idProy;
    }
}
