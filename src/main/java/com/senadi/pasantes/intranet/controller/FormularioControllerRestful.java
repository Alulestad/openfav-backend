package com.senadi.pasantes.intranet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.senadi.pasantes.intranet.service.IFormularioService;
import com.senadi.pasantes.intranet.service.to.FormularioTO;

@CrossOrigin
@RestController
@RequestMapping(path = "/Formularios")
public class FormularioControllerRestful {

	@Autowired
	private IFormularioService iFormularioService;

	// BUSCAR POR ID
	// http://localhost:8086/API/v1.0/Intranet/Formularios/{id} GET
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FormularioTO> buscarPorId(@PathVariable Integer id) throws JsonProcessingException {
		var FormularioTo = this.iFormularioService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(FormularioTo);
	}

	// INSERTAR
	// http://localhost:8086/API/v1.0/Intranet/Formularios POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertar(@RequestBody FormularioTO FormularioTO) throws JsonProcessingException {
		this.iFormularioService.insertar(FormularioTO);
	}

	// ACTUALIZAR
	// http://localhost:8086/API/v1.0/Intranet/Formularios/{id} PUT
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@PathVariable Integer id, @RequestBody FormularioTO FormularioTO) {
		FormularioTO.setId(id);
		this.iFormularioService.actualizar(FormularioTO);
	}

	// BORRAR
	// http://localhost:8086/API/v1.0/Intranet/Formularios/{id} DELETE
	@DeleteMapping(path = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		this.iFormularioService.eliminar(id);
	}
}