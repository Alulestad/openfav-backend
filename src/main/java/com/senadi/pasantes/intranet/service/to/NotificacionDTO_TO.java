package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class NotificacionDTO_TO extends RepresentationModel<NotificacionDTO_TO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDateTime fechaInicio;

	private LocalDateTime fechaFin;

	private String importancia;

	private String estado;
	
	private String nombre;
	
	
	@Override
	public String toString() {
		return "NotificacionDTO_TO [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", importancia=" + importancia + ", estado=" + estado + "]";
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
