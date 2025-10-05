package com.senadi.pasantes.intranet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senadi.pasantes.intranet.service.IUsuarioService;
import com.senadi.pasantes.intranet.service.to.UsuarioLoginTO;
import com.senadi.pasantes.intranet.service.to.UsuarioTO;


@RestController
@RequestMapping(path = "/Usuarios")
@CrossOrigin
public class UsuarioControllerRestful {

	@Autowired
	private IUsuarioService iUsuarioService;

	// BUSCAR POR ID
	// http://localhost:8086/API/v1.0/Intranet/Usuarios/{id} GET
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioTO> buscarPorId(@PathVariable Integer id) {
		var UsuarioTo = this.iUsuarioService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(UsuarioTo);
	}

	// INSERTAR
	// http://localhost:8086/API/v1.0/Intranet/Usuarios POST
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> insertar(@RequestBody UsuarioTO UsuarioTO) {
		Integer respuesta=this.iUsuarioService.insertar(UsuarioTO);
		System.out.println("Esta les la respuesta de la bandera: "+respuesta);
		return ResponseEntity.status(HttpStatus.OK).body(respuesta);
	}

	// ACTUALIZAR
	// http://localhost:8086/API/v1.0/Intranet/Usuarios/{id} PUT
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void actualizar(@PathVariable Integer id, @RequestBody UsuarioTO UsuarioTO) {
		UsuarioTO.setId(id);
		this.iUsuarioService.actualizar(UsuarioTO);
	}

	// BORRAR
	// http://localhost:8086/API/v1.0/Intranet/Usuarios/{id} DELETE
	@DeleteMapping(path = "/{id}")
	public void eliminar(@PathVariable Integer id) {
		this.iUsuarioService.eliminar(id);
	}
	
	
	// Validar
	// http://localhost:8086/API/v1.0/Intranet/Usuarios/intentLogin GET
	@PatchMapping (path = "/intentLogin",consumes =  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Integer> verificarCredenciales(@RequestBody UsuarioLoginTO usuarioLoginTO) {
		System.out.println("adf: "+usuarioLoginTO);
		var identificado = this.iUsuarioService.validarUsuario(usuarioLoginTO);
		return ResponseEntity.status(HttpStatus.OK).body(identificado);
	}
	
}