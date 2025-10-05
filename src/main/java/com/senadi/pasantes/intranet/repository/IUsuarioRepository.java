package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.repository.modelo.Usuario;
import com.senadi.pasantes.intranet.repository.modelo.dto.UsuarioLoginDTO;

public interface IUsuarioRepository {

	public void insertar(Usuario usuario);
	public Usuario buscarPorId(Integer id);
	public void actualizar(Usuario usuario);
	public void eliminar(Integer id);
	public UsuarioLoginDTO seleccionarPorCedulaUsuarioLoginDTO(String cedula);
	public UsuarioLoginDTO seleccionarPorCorreoUsuarioLoginDTO(String email);
}
