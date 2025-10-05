package com.senadi.pasantes.intranet.repository.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 */
/**
 * 
 */
@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
	@Column(name = "user_id")
	private Integer id;

	@Column(name = "user_cedula")
	private String cedula;

	@Column(name = "user_identificacion_senadi")
	private String identificacionSenadi;

	@Column(name = "user_nombres")
	private String nombres;

	@Column(name = "user_apellidos")
	private String apellidos;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_email")
	private String email;

	@Column(name = "user_rol")
	private String rol;
	
	@Column(name = "user_nivel_flujo")
	private String nivelFlujo;

	@Column(name = "user_departamento")
	private String departamento;

	// Relaciones
	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Notificacion> notificaciones;

	@OneToMany(mappedBy = "usuario")
	private List<Log> logs;

	@OneToMany(mappedBy = "emisor")
	private List<Formulario> formularios;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
	private List<Documento> documentos;

	// Get y set
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
	
	

	public String getNivelFlujo() {
		return nivelFlujo;
	}

	public void setNivelFlujo(String nivelFlujo) {
		this.nivelFlujo = nivelFlujo;
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

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
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
	
	

}
