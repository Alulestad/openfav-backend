package com.senadi.pasantes.intranet.repository.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class Log {

	@Id
	@SequenceGenerator(name = "seq_log", sequenceName = "seq_log", allocationSize = 1)
	@GeneratedValue(generator = "seq_log", strategy = GenerationType.SEQUENCE)
	@Column(name = "log_id")
	private Integer id;

	@Column(name = "log_accion")
	private String accion;

	@Column(name = "log_fecha_accion")
	private LocalDateTime fechaAccion;

	// Relaciones
	@ManyToOne
	@JoinColumn(name = "log_user_id")
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
