package com.senadi.pasantes.intranet.repository.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "notificacion")
public class Notificacion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_notificacion")
	@SequenceGenerator(name = "seq_notificacion", sequenceName = "seq_notificacion", allocationSize = 1)
	@Column(name = "ntfc_id")
	private Integer id;

	@Column(name = "ntfc_fecha_inicio")
	private LocalDateTime fechaInicio;

	@Column(name = "ntfc_fecha_fin")
	private LocalDateTime fechaFin;

	@Column(name = "ntfc_url_imagen", columnDefinition = "text")
	private String urlImagen;

	@Column(name = "ntfc_importancia")
	private String importancia;

	@Column(name = "ntfc_estado")
	private String estado;

	@Column(name = "ntfc_nombre")
	private String nombre;

	// Relaciones
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ntfc_id_usuario")
	private Usuario usuario;

	// Get y set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public String getImportancia() {
		return importancia;
	}

	public void setImportancia(String importancia) {
		this.importancia = importancia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Notificacion [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", importancia="
				+ importancia + ", estado=" + estado + ", nombre=" + nombre + " ]";
	}

}
