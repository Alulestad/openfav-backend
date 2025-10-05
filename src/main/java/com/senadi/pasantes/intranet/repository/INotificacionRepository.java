package com.senadi.pasantes.intranet.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.senadi.pasantes.intranet.repository.modelo.Notificacion;
import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionImagenDTO;

public interface INotificacionRepository {

	public void insertar(Notificacion notificacion);

	public Notificacion buscarPorId(Integer id);

	public List<Notificacion> buscarTodos();

	public List<NotificacionDTO> consultarTodoDTO();

	public NotificacionImagenDTO buscarPorIdSoloImagen(Integer id);

	public Integer actualizar(Notificacion notificacion);

	public void eliminar(Integer id);

	List<NotificacionImagenDTO> buscarPorfecha(LocalDateTime fechaActual);
}
