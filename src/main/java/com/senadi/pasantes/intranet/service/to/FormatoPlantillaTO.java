package com.senadi.pasantes.intranet.service.to;

import java.time.LocalDateTime;

public class FormatoPlantillaTO {

	
	private Integer id;
	private String nombre;
	private LocalDateTime fechaCreacion;
	private LocalDateTime fechaActualizacion;
	private String documento;
	private String tipo;
	private String categoria;
	public FormatoPlantillaTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormatoPlantillaTO( Integer id, String nombre, LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion,String documento,
			 String tipo, String categoria) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.fechaActualizacion = fechaActualizacion;
		this.documento = documento;
		this.tipo = tipo;
		this.categoria= categoria;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	
	
}
