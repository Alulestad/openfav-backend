package com.senadi.pasantes.intranet.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.senadi.pasantes.intranet.repository.IUsuarioRepository;
import com.senadi.pasantes.intranet.repository.modelo.Usuario;
import com.senadi.pasantes.intranet.repository.modelo.dto.UsuarioLoginDTO;
import com.senadi.pasantes.intranet.security.EncriptacionContrasenia;
import com.senadi.pasantes.intranet.service.to.UsuarioLoginTO;
import com.senadi.pasantes.intranet.service.to.UsuarioTO;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	private final ModelMapper modelMapper = new ModelMapper();

	private UsuarioTO convertirATO(Usuario usuario) {
		return modelMapper.map(usuario, UsuarioTO.class);
	}

	private Usuario convertirAUsuario(UsuarioTO usuarioTO) {
		return modelMapper.map(usuarioTO, Usuario.class);
	}

	@Autowired
	private IUsuarioRepository iUsuarioRepo;

	@Autowired
	private EncriptacionContrasenia encriptacionContrasenia;
	
	 @Autowired
	  private PasswordEncoder passwordEncoder; // Inteccion PasswordEncoder

	@Override
	public Integer insertar(UsuarioTO usuarioTO) {
	    if (usuarioTO.getCedula() != null
	            && this.iUsuarioRepo.seleccionarPorCedulaUsuarioLoginDTO(usuarioTO.getCedula()) == null
	            && this.iUsuarioRepo.seleccionarPorCorreoUsuarioLoginDTO(usuarioTO.getEmail()) == null) {

	        // Hashear la contraseña antes de almacenarla
	        String hashedPassword = passwordEncoder.encode(usuarioTO.getPassword());
	        usuarioTO.setPassword(hashedPassword);

	        // Guardar el usuario con la contraseña hasheada
	        this.iUsuarioRepo.insertar(this.convertirAUsuario(usuarioTO));

	        // Verificar si el usuario se insertó correctamente
	        if (this.iUsuarioRepo.seleccionarPorCedulaUsuarioLoginDTO(usuarioTO.getCedula()) != null) {
	            return 1;
	        }
	    } else {
	        return -2;
	    }

	    return 0;
	}

	@Override
	public UsuarioTO buscarPorId(Integer id) {
		return this.convertirATO(this.iUsuarioRepo.buscarPorId(id));
	}

	@Override
	public void actualizar(UsuarioTO usuarioTO) {
		this.iUsuarioRepo.actualizar(this.convertirAUsuario(usuarioTO));
	}

	@Override
	public void eliminar(Integer id) {
		this.iUsuarioRepo.eliminar(id);
	}

	@Override
	public Integer validarUsuario(UsuarioLoginTO usuarioLoginTO) {
	    try {
	        UsuarioLoginDTO usuarioLoginDTO = this.iUsuarioRepo
	                .seleccionarPorCedulaUsuarioLoginDTO(usuarioLoginTO.getCedula());

	        // Verificar la contraseña ingresada contra el hash almacenado
	        if (usuarioLoginDTO != null && passwordEncoder.matches(usuarioLoginTO.getPassword(), usuarioLoginDTO.getPassword())) {
	            System.out.println("La contraseña coincide");

	            if (usuarioLoginTO.getRol().equals(usuarioLoginDTO.getRol())) {
	                System.out.println("El rol es correcto");
	                return usuarioLoginDTO.getId();
	            }
	        } else {
	            System.out.println("La contraseña no coincide");
	        }

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    // Segunda verificación por correo, en caso de no encontrar por cédula
	    try {
	        UsuarioLoginDTO usuarioLoginDTOEmail = this.iUsuarioRepo
	                .seleccionarPorCorreoUsuarioLoginDTO(usuarioLoginTO.getEmail());

	        if (usuarioLoginDTOEmail != null && passwordEncoder.matches(usuarioLoginTO.getPassword(), usuarioLoginDTOEmail.getPassword())) {
	            System.out.println("La contraseña coincide");

	            if (usuarioLoginTO.getRol().equals(usuarioLoginDTOEmail.getRol())) {
	                System.out.println("El rol es correcto");
	                return usuarioLoginDTOEmail.getId();
	            }
	        } else {
	            System.out.println("La contraseña no coincide");
	        }

	    } catch (Exception e) {
	        System.out.println(e.getMessage());
	    }

	    return -1; // Retorno de error si no coincide o si hay alguna excepción
	}


}