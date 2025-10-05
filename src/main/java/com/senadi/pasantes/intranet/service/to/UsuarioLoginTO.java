package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.senadi.pasantes.intranet.repository.modelo.Documento;
import com.senadi.pasantes.intranet.repository.modelo.Formulario;
import com.senadi.pasantes.intranet.repository.modelo.Log;
import com.senadi.pasantes.intranet.repository.modelo.Notificacion;

public class UsuarioLoginTO extends RepresentationModel<UsuarioLoginTO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1714083382412329352L;

	private String cedula;
	private String email;
	private String password;
	private String rol;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UsuarioLoginTO [cedula=" + cedula + ", email=" + email + ", rol=" + rol + "]";
	}

}
