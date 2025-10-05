package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.senadi.pasantes.intranet.repository.modelo.Usuario;

public class DocumentoListaTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nombre;
	private String categoria;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
	private String documento;
	private String documentoURL;
	private String tipo;
	private String estado;

	// Relaciones
	private Usuario usuario;

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", fechaCreacion="
				+ fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + ", documento=" + documento
				+ ", documentoURL=" + documentoURL + ", tipo=" + tipo + ", estado=" + estado + "]";
	}

}