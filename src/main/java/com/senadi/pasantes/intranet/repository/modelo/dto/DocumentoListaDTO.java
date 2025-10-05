package com.senadi.pasantes.intranet.repository.modelo.dto;

public class DocumentoListaDTO {
	
	private Integer id;
	private String nombre;
	private String categoria;
	private String documento;
	private String documentoURL;
	private String tipo;
	private String estado;
	
	
	public DocumentoListaDTO() {
		super();
	}
	
	public DocumentoListaDTO(Integer id, String nombre, String categoria, String documento, String documentoURL,
			String tipo, String estado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.documento = documento;
		this.documentoURL = documentoURL;
		this.tipo = tipo;
		this.estado = estado;
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
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getDocumentoURL() {
		return documentoURL;
	}
	public void setDocumentoURL(String documentoURL) {
		this.documentoURL = documentoURL;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "DocumentoListaDTO [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", documento="
				+ documento + ", documentoURL=" + documentoURL + ", tipo=" + tipo + ", estado=" + estado + "]";
	}
	
	
	
}
