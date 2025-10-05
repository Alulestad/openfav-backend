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

import com.senadi.pasantes.intranet.service.ILogService;
import com.senadi.pasantes.intranet.service.to.LogTO;

@CrossOrigin
@RestController
@RequestMapping(path = "/Logs")
public class LogControllerRestful {

	@Autowired
	private ILogService iLogService;

	// BUSCAR POR ID
	// http://localhost:8086/API/v1.0/Intranet/Logs/{id} GET
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LogTO> buscarPorId(@PathVariable Integer id) {
		var LogTo = this.iLogService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(LogTo);
	}

	// INSERTAR
	// http://localhost:8086/API/v1.0/Intranet/Logs POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void insertar(@RequestBody LogTO LogTO) {
		this.iLogService.insertar(LogTO);
	}

	// ACTUALIZAR
	// http://localhost:8086/API/v1.0/Intranet/Logs/{id} PUT
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@PathVariable Integer id, @RequestBody LogTO LogTO) {
		LogTO.setId(id);
		this.iLogService.actualizar(LogTO);
	}

	// BORRAR
	// http://localhost:8086/API/v1.0/Intranet/Logs/{id} DELETE
	@DeleteMapping(path = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		this.iLogService.eliminar(id);
	}
}