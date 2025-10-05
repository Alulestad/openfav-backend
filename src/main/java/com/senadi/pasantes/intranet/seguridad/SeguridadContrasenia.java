package com.senadi.pasantes.intranet.seguridad;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SeguridadContrasenia {

	private static final BCryptPasswordEncoder codificador = new BCryptPasswordEncoder();

	// Método para registrar una nueva contraseña
	public static String hashearContrasena(String contrasenaEnTextoPlano) {
		return codificador.encode(contrasenaEnTextoPlano);
	}

	// Método para verificar una contraseña
	public static boolean verificarContrasena(String contrasenaEnTextoPlano, String contrasenaHasheada) {
		return codificador.matches(contrasenaEnTextoPlano, contrasenaHasheada);
	}

}
