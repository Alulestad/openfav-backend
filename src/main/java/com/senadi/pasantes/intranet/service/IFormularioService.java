package com.senadi.pasantes.intranet.service;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.senadi.pasantes.intranet.service.to.FormularioTO;

public interface IFormularioService {

	public void insertar(FormularioTO formularioTO) throws JsonProcessingException;

	public FormularioTO buscarPorId(Integer id) throws JsonProcessingException;

	public void actualizar(FormularioTO formularioTO);

	public void eliminar(Integer id);

	void insertarHATEOAS(FormularioTO formularioTO) throws JsonProcessingException;

	FormularioTO buscarPorIdHATEOAS(Integer id) throws JsonProcessingException;

	void actualizarHATEOAS(FormularioTO formularioTO);

	void eliminarHATEOAS(Integer id);

}
