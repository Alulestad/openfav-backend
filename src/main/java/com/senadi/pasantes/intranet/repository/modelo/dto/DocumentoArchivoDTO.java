package com.senadi.pasantes.intranet.repository.modelo.dto;

public class DocumentoArchivoDTO {

	private String nombre;

	private String documento;

	private String tipo;

	public DocumentoArchivoDTO() {
		super();
	}

	public DocumentoArchivoDTO(String nombre, String documento, String tipo) {
		super();
		this.nombre = nombre;
		this.documento = documento;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "DocumentoArchivoDTO [nombre=" + nombre + ", documento=" + documento + ", tipo=" + tipo + "]";
	}

	// Get y set
	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}