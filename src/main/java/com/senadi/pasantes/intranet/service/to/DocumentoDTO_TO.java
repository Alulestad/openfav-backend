package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

public class DocumentoDTO_TO extends RepresentationModel<DocumentoDTO_TO> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nombre;

	private String categoria;

	private String estado;

	@Override
	public String toString() {
		return "DocumentoDTO_TO [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", estado=" + estado
				+ "]";
	}

	// Get y set
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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}