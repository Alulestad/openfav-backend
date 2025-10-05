package com.openfav.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "RELATIONSHIP_1")
public class OngProyecto {

    @EmbeddedId
    private OngProyectoId id = new OngProyectoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ongId")
    @JoinColumn(name = "ID_ONG")
    private Ong ong;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("proyectoId")
    @JoinColumn(name = "ID_PROY")
    private Proyecto proyecto;

    public OngProyecto() {
    }

    public OngProyecto(Ong ong, Proyecto proyecto) {
        this.ong = ong;
        this.proyecto = proyecto;
        this.id = new OngProyectoId(ong.getId(), proyecto.getId());
    }

    public OngProyectoId getId() {
        return id;
    }

    public void setId(OngProyectoId id) {
        this.id = id;
    }

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public static class OngProyectoId implements Serializable {
        private static final long serialVersionUID = 1L;

        @Column(name = "ID_ONG")
        private Integer ongId;

        @Column(name = "ID_PROY")
        private Integer proyectoId;

        public OngProyectoId() {
        }

        public OngProyectoId(Integer ongId, Integer proyectoId) {
            this.ongId = ongId;
            this.proyectoId = proyectoId;
        }

        public Integer getOngId() {
            return ongId;
        }

        public void setOngId(Integer ongId) {
            this.ongId = ongId;
        }

        public Integer getProyectoId() {
            return proyectoId;
        }

        public void setProyectoId(Integer proyectoId) {
            this.proyectoId = proyectoId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            OngProyectoId that = (OngProyectoId) o;
            return Objects.equals(ongId, that.ongId) && Objects.equals(proyectoId, that.proyectoId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ongId, proyectoId);
        }
    }
}
