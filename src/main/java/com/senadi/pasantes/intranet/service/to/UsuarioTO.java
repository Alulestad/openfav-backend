package com.senadi.pasantes.intranet.service.to;

import java.io.Serializable;
import java.util.List;

import com.senadi.pasantes.intranet.repository.modelo.Documento;
import com.senadi.pasantes.intranet.repository.modelo.Formulario;
import com.senadi.pasantes.intranet.repository.modelo.Log;
import com.senadi.pasantes.intranet.repository.modelo.Notificacion;

public class UsuarioTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String cedula;
	private String identificacionSenadi;
	private String nombres;
	private String apellidos;
	private String password;
	private String email;
	private String rol;
	private String departamento;

	// Relaciones
	private List<Notificacion> notificaciones;
	private List<Log> log;
	private List<Formulario> formularios;
	private List<Documento> documentos;

	// Get y Set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getIdentificacionSenadi() {
		return identificacionSenadi;
	}

	public void setIdentificacionSenadi(String identificacionSenadi) {
		this.identificacionSenadi = identificacionSenadi;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public List<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void setNotificaciones(List<Notificacion> notificaciones) {
		this.notificaciones = notificaciones;
	}

	public List<Log> getLog() {
		return log;
	}

	public void setLog(List<Log> log) {
		this.log = log;
	}

	public List<Formulario> getFormularios() {
		return formularios;
	}

	public void setFormularios(List<Formulario> formularios) {
		this.formularios = formularios;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	@Override
	public String toString() {
		return "UsuarioTO [id=" + id + ", cedula=" + cedula + ", identificacionSenadi=" + identificacionSenadi
				+ ", nombres=" + nombres + ", apellidos=" + apellidos + ", password=" + password + ", email=" + email
				+ ", rol=" + rol + ", departamento=" + departamento + "]";
	}

}
