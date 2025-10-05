package com.senadi.pasantes.intranet.service;

import java.util.List;

import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionImagenDTO;
import com.senadi.pasantes.intranet.service.to.NotificacionDTO_TO;
import com.senadi.pasantes.intranet.service.to.NotificacionTO;

public interface INotificacionService {

	public void insertar(NotificacionTO notificacionTO);

	public NotificacionTO buscarPorId(Integer id);

	public List<NotificacionTO> buscarTodos();

	public Integer actualizar(NotificacionTO notificacionTO);

	public void eliminar(Integer id);

	List<NotificacionImagenDTO> obtenerNotificaciones();

	public Integer cambiarEstado(Integer idImg, Integer idAdmin);

	public List<NotificacionDTO_TO> consultarTodoDTO();

	public NotificacionImagenDTO buscarPorIdSoloImagen(Integer id);

}
