package com.senadi.pasantes.intranet.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.senadi.pasantes.intranet.repository.modelo.Usuario;
import com.senadi.pasantes.intranet.repository.modelo.dto.UsuarioLoginDTO;

@Repository
@Transactional
public class UsuarioRepositoryImpl implements IUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Usuario usuario) {
		
		this.entityManager.persist(usuario);
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		try {
			return this.entityManager.find(Usuario.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void actualizar(Usuario usuario) {
		this.entityManager.merge(usuario);

	}

	@Override
	public void eliminar(Integer id) {
		Usuario usuario = this.buscarPorId(id);
		this.entityManager.remove(usuario);
	}

	@Override
	public UsuarioLoginDTO seleccionarPorCedulaUsuarioLoginDTO(String cedula) {
		UsuarioLoginDTO usuarioLoginDTO=null;
		
		try {
			TypedQuery<UsuarioLoginDTO> myQuery = this.entityManager.createQuery(""
					+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.UsuarioLoginDTO(u.id, u.cedula, u.password, u.rol) "
					+ "from Usuario u where u.cedula=:datoCedula", UsuarioLoginDTO.class);
			myQuery.setParameter("datoCedula", cedula);
			
			usuarioLoginDTO=myQuery.getSingleResult();
			
		}catch (Exception e) {
			//e.printStackTrace();
		}

		

		return usuarioLoginDTO;
	}

	@Override
	public UsuarioLoginDTO seleccionarPorCorreoUsuarioLoginDTO(String email) {
		UsuarioLoginDTO usuarioLoginDTO=null;
		
		try {
		TypedQuery<UsuarioLoginDTO> myQuery = this.entityManager.createQuery(""
				+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.UsuarioLoginDTO(u.id, u.cedula, u.password, u.rol) "
				+ "from Usuario u where u.email=:datoEmail", UsuarioLoginDTO.class);
		myQuery.setParameter("datoEmail", email);
		usuarioLoginDTO=myQuery.getSingleResult();
		}catch (Exception e) {
			//e.printStackTrace();
		}

		return usuarioLoginDTO;
	}

}
