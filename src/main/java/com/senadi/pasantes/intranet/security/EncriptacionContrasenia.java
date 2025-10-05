package com.senadi.pasantes.intranet.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EncriptacionContrasenia {

	private final BCryptPasswordEncoder encriptadorDeContraseña = new BCryptPasswordEncoder();

	public String encriptarContraseña(String contraseña) {
		return encriptadorDeContraseña.encode(contraseña);
	}

	public boolean verificarCoincidencia(String contraseñaSinEncriptar, String contraseñaEncriptada) {
		return encriptadorDeContraseña.matches(contraseñaSinEncriptar, contraseñaEncriptada);
	}
}
