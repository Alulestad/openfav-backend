package com.senadi.pasantes.intranet.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.senadi.pasantes.intranet.repository.modelo.Formulario;

@Repository
@Transactional
public class FormularioRepositoryImpl implements IFormularioRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Formulario formulario) {
        System.out.println("Contenido Formulario: "+formulario.getContenido());
        System.out.println("Contenido Formulario TIpo: "+formulario.getContenido().getClass().getName());

		this.entityManager.persist(formulario);
	}

	@Override
	public Formulario buscarPorId(Integer id) {
		return this.entityManager.find(Formulario.class, id);
	}

	@Override
	public void actualizar(Formulario formulario) {
		this.entityManager.merge(formulario);
	}

	@Override
	public void eliminar(Integer id) {
		Formulario formulario = this.buscarPorId(id);
		this.entityManager.remove(formulario);
	}

}
