package com.senadi.pasantes.intranet.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.senadi.pasantes.intranet.repository.modelo.Notificacion;
import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionImagenDTO;

@Repository
@Transactional
public class NotificacionRepositoryImpl implements INotificacionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Notificacion notificacion) {
		this.entityManager.persist(notificacion);
	}

	@Override
	public Notificacion buscarPorId(Integer id) {
		return this.entityManager.find(Notificacion.class, id);
	}

	@Override
	public List<NotificacionDTO> consultarTodoDTO() {
		Query q = this.entityManager.createQuery(
				"SELECT NEW com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionDTO(n.id, n.fechaInicio, n.fechaFin, n.importancia, n.estado, n.nombre) FROM Notificacion n");
		return q.getResultList();
	}

	@Override
	public NotificacionImagenDTO buscarPorIdSoloImagen(Integer id) {
		Query q = this.entityManager.createQuery(
				"SELECT NEW com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionImagenDTO(n.id, n.urlImagen, n.fechaInicio, n.fechaFin, n.importancia, n.estado, n.nombre) FROM Notificacion n WHERE n.id =: id");
		q.setParameter("id", id);
		return (NotificacionImagenDTO) q.getSingleResult();
	}

	@Override
	public Integer actualizar(Notificacion notificacion) {
		try {
			this.entityManager.merge(notificacion);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public void eliminar(Integer id) {
		Notificacion notificacion = this.buscarPorId(id);
		this.entityManager.remove(notificacion);
	}

	@Override
	public List<Notificacion> buscarTodos() {
		TypedQuery<Notificacion> myQuery = this.entityManager.createQuery("" + "select d from Notificacion d",
				Notificacion.class);

		return myQuery.getResultList();
	}

	public List<NotificacionImagenDTO> buscarPorfecha(LocalDateTime fechaActual) {
	    String jpql = "SELECT NEW com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionImagenDTO(n.id, n.urlImagen, n.fechaInicio, n.fechaFin, n.importancia, n.estado, n.nombre) "
	                 + "FROM Notificacion n "
	                 + "WHERE :fechaActual BETWEEN n.fechaInicio AND n.fechaFin "
	                 + "AND n.estado = 'A' "
	                 + "ORDER BY CASE "
	                 + "    WHEN n.importancia = 'alta' THEN 1 "
	                 + "    WHEN n.importancia = 'media' THEN 2 "
	                 + "    WHEN n.importancia = 'baja' THEN 3 "
	                 + "    ELSE 4 "
	                 + "END";

	    Query myQuery = this.entityManager.createQuery(jpql);
	    myQuery.setParameter("fechaActual", fechaActual);

	    return myQuery.getResultList();
	}

}
