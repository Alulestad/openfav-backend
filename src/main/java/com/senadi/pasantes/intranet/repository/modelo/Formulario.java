package com.senadi.pasantes.intranet.repository.modelo;

import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.json.JsonStringType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


@Entity
@Table(name = "formulario")
//@TypeDef(name = "json", typeClass = JsonStringType.class)
public class Formulario {
	
	
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_formulario")
	@SequenceGenerator(name = "seq_formulario", sequenceName = "seq_formulario", allocationSize = 1)
	@Column(name = "form_id")
	private Integer id;

	@Column(name = "form_fecha_emision")
	private LocalDateTime fechaEmision;

	@Column(name = "form_fecha_tratamiento")
	private LocalDateTime fechaTratamiento;

	@Column(name = "form_estado")
	private String estado;

	@Column(name = "form_id_destinatario")
	private Integer idDestinatario;

	// @JdbcTypeCode(SqlTypes.JSON)
	// el tipo de dato es JSON, realica la conversión adecuada entre el tipo Java
	// (en este caso, String) y el tipo jsonb de PostgreSQL.
	//@Type(type = "json")
	//@Column(name = "form_contenido", columnDefinition = "jsonb")
	@Column(name = "form_contenido", columnDefinition = "text")
	//@Convert(converter = JsonbConverter.class)
	//private Map<String, Object> contenido;
	private String contenido;

	// Relaciones
	@ManyToOne()
	@JoinColumn(name = "form_id_emisor")
	private Usuario emisor;

	// Get y set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(LocalDateTime fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public LocalDateTime getFechaTratamiento() {
		return fechaTratamiento;
	}

	public void setFechaTratamiento(LocalDateTime fechaTratamiento) {
		this.fechaTratamiento = fechaTratamiento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Integer getIdDestinatario() {
		return idDestinatario;
	}

	public void setIdDestinatario(Integer idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

//	public Map<String, Object> getContenido() {
//		return contenido;
//	}
//
//	public void setContenido(Map<String, Object> contenido) {
//		this.contenido = contenido;
//	}

	


	
	

}
