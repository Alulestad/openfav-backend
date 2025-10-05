package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.service.to.UsuarioLoginTO;
import com.senadi.pasantes.intranet.service.to.UsuarioTO;

public interface IUsuarioService {

	public Integer insertar(UsuarioTO usuarioTO);

	public UsuarioTO buscarPorId(Integer id);

	public void actualizar(UsuarioTO usuarioTO);

	public void eliminar(Integer id);
	
	public Integer validarUsuario(UsuarioLoginTO usuarioLoginTO);
	


}
