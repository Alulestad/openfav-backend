package com.senadi.pasantes.intranet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.senadi.pasantes.intranet.repository.IDocumentoRepository;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoInstructivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoNormativaDTO;
import com.senadi.pasantes.intranet.service.INotificacionService;

import antlr.collections.List;

@SpringBootApplication
public class PsIsEisCdelBackendApplication implements CommandLineRunner {

	@Autowired
	INotificacionService iNotificacionService;
	
	@Autowired
	IDocumentoRepository documentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(PsIsEisCdelBackendApplication.class, args);
	}

	 // Define el bean de PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Ok, backend");

//		var notificaciones = iNotificacionService.obtenerNotificaciones();
//		for (NotificacionImagenDTO notificacion : notificaciones) {
//			System.out.println(notificacion);
//		}
		
		
		/*
			var c=	documentoRepository.consultarInstructivosDTO();
			for (DocumentoInstructivoDTO documentoInstructivoDTO : c) {
				System.out.println(documentoInstructivoDTO);
			}
		*/
		
	/*	var c=	documentoRepository.consultarNormativasDTO();
		System.out.println("Longitud: "+ c.size());
		for (DocumentoNormativaDTO documentoNormativaDTO : c) {
			System.out.println(documentoNormativaDTO);
		}*/
		
		
		
	}

}
