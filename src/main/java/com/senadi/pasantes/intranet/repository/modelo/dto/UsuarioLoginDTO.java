package com.senadi.pasantes.intranet.repository.modelo.dto;

public class UsuarioLoginDTO {

	private Integer id;
	private String cedula;
	private String password;
	private String rol;

	public UsuarioLoginDTO() {
		super();
	}

	public UsuarioLoginDTO(Integer id, String cedula, String password, String rol) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.password = password;
		this.rol = rol;
	}

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

}
