package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.senadi.pasantes.intranet.repository.modelo.Usuario;

public class LogTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String accion;
	private LocalDateTime fechaAccion;

	// Relaciones
	private Usuario usuario;

	// Get y set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public LocalDateTime getFechaAccion() {
		return fechaAccion;
	}

	public void setFechaAccion(LocalDateTime fechaAccion) {
		this.fechaAccion = fechaAccion;
	}

	@Override
	public String toString() {
		return "LogTo [id=" + id + ", accion=" + accion + ", fechaAccion=" + fechaAccion + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
