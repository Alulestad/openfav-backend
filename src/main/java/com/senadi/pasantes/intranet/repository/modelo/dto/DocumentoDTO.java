package com.senadi.pasantes.intranet.repository.modelo.dto;

public class DocumentoDTO {

	private Integer id;

	private String nombre;

	private String categoria;

	private String estado;

	public DocumentoDTO() {
		super();
	}

	public DocumentoDTO(Integer id, String nombre, String categoria, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "DocumentoDTO [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", estado=" + estado
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