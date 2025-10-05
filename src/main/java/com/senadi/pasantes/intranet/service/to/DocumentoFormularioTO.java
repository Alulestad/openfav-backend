package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

public class DocumentoFormularioTO extends RepresentationModel<DocumentoFormularioTO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
	private String tipo;

	public DocumentoFormularioTO() {
		super();
	}

	public DocumentoFormularioTO(Integer id, String nombre, LocalDateTime fechaCreacion,
			LocalDateTime fechaActualizacio, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.tipo = tipo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public LocalDateTime getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DocumentoFormularioDTO [id=" + id + ", nombre=" + nombre + ", fechaCreacion=" + fechaCreacion
				+ ", fechaActualizacion=" + fechaActualizacion + ", tipo=" + tipo + "]";
	}

}
