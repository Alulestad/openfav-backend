package com.senadi.pasantes.intranet.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.senadi.pasantes.intranet.repository.modelo.Log;

@Repository
@Transactional
public class LogRepositotyImpl implements ILogRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Log log) {
		this.entityManager.persist(log);
	}

	@Override
	public Log buscarPorId(Integer id) {
		return this.entityManager.find(Log.class, id);
	}

	@Override
	public void actualizar(Log log) {
		this.entityManager.merge(log);
	}

	@Override
	public void eliminar(Integer id) {
		this.entityManager.remove(this.buscarPorId(id));
	}

}
