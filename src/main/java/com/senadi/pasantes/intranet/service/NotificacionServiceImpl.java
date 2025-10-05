package com.senadi.pasantes.intranet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senadi.pasantes.intranet.repository.INotificacionRepository;
import com.senadi.pasantes.intranet.repository.IUsuarioRepository;
import com.senadi.pasantes.intranet.repository.modelo.Notificacion;
import com.senadi.pasantes.intranet.repository.modelo.Usuario;
import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.NotificacionImagenDTO;
import com.senadi.pasantes.intranet.service.to.LogTO;
import com.senadi.pasantes.intranet.service.to.NotificacionDTO_TO;
import com.senadi.pasantes.intranet.service.to.NotificacionTO;

@Service
public class NotificacionServiceImpl implements INotificacionService {

	private final ModelMapper modelMapper = new ModelMapper();

	private NotificacionTO convertirATO(Notificacion notificacion) {
		return modelMapper.map(notificacion, NotificacionTO.class);
	}

	private Notificacion convertirANotificacion(NotificacionTO notificacionTO) {
		return modelMapper.map(notificacionTO, Notificacion.class);
	}

	private NotificacionDTO_TO convertirANotificacionDTO_TO(NotificacionDTO notificacionDTO) {
		return modelMapper.map(notificacionDTO, NotificacionDTO_TO.class);
	}

	@Autowired
	private INotificacionRepository iNotificacionRepo;

	@Autowired
	private ILogService iLogService;

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	public void insertar(NotificacionTO notificacionTO) {
		this.iNotificacionRepo.insertar(this.convertirANotificacion(notificacionTO));
	}

	@Override
	public NotificacionTO buscarPorId(Integer id) {
		return this.convertirATO(this.iNotificacionRepo.buscarPorId(id));
	}

	@Override
	public List<NotificacionTO> buscarTodos() {
		List<Notificacion> notificacions = this.iNotificacionRepo.buscarTodos();
		List<NotificacionTO> notificacionTOs = new ArrayList<>();

		for (Notificacion noti : notificacions) {
			notificacionTOs.add(this.convertirATO(noti));
		}

		return notificacionTOs;
	}

	@Override
	public List<NotificacionDTO_TO> consultarTodoDTO() {
		List<NotificacionDTO> pdto = this.iNotificacionRepo.consultarTodoDTO();
		List<NotificacionDTO_TO> lsdto_to = new ArrayList<>();
		for (NotificacionDTO notificacionDTO : pdto) {
			lsdto_to.add(this.convertirANotificacionDTO_TO(notificacionDTO));
		}
		return lsdto_to;
	}

	@Override
	public NotificacionImagenDTO buscarPorIdSoloImagen(Integer id) {
		return this.iNotificacionRepo.buscarPorIdSoloImagen(id);
	}

	@Override
	public Integer actualizar(NotificacionTO notificacionTO) {
		var notificacionActualizar = this.iNotificacionRepo.buscarPorId(notificacionTO.getId());
		notificacionActualizar.setEstado(notificacionTO.getEstado());
		notificacionActualizar.setImportancia(notificacionTO.getImportancia());
		notificacionActualizar.setFechaInicio(notificacionTO.getFechaInicio());
		notificacionActualizar.setFechaFin(notificacionTO.getFechaFin());
		notificacionActualizar.setUsuario(notificacionTO.getUsuario());
		notificacionActualizar.setNombre(notificacionTO.getNombre());

		if (notificacionTO.getUrlImagen() != null) {
			notificacionActualizar.setUrlImagen(notificacionTO.getUrlImagen());
		}

		Integer act = -1;
		try {
			// LOGS
			LogTO logTO = new LogTO();
			String accion = String.format(
					"Modifico a partir de: [ntfc_id: %s, " + "ntfc_fecha_inicio: %s, " + "ntfc_fecha_fin: %s, "
							+ "ntfc_estado: %s, " + "ntfc_importancia: %s]",
					notificacionTO.getId(), notificacionTO.getFechaInicio(), notificacionTO.getFechaFin(),
					notificacionTO.getEstado(), notificacionTO.getImportancia());

			logTO.setFechaAccion(LocalDateTime.now());
			logTO.setAccion(accion);
			logTO.setUsuario(this.iUsuarioRepository.buscarPorId(notificacionTO.getId()));

			this.iLogService.insertar(logTO);

			act = this.iNotificacionRepo.actualizar(notificacionActualizar);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return act;

	}

	@Override
	public void eliminar(Integer id) {
		this.iNotificacionRepo.eliminar(id);
	}

	@Override
	public List<NotificacionImagenDTO> obtenerNotificaciones() {
		LocalDateTime fechaActual = LocalDateTime.now();
		return this.iNotificacionRepo.buscarPorfecha(fechaActual);
	}

	@Override
	public Integer cambiarEstado(Integer idImg, Integer idAdmin) {
		Notificacion img = this.iNotificacionRepo.buscarPorId(idImg);
		System.out.println("ImagenServiceImpl>cambiarEstado>img: " + img);
		try {
			// LOGS
			LogTO logTO = new LogTO();
			String accion = String.format(
					"Modifico a partir de: [ntfc_id: %s, " + "ntfc_estado: %s, " + "ntfc_fecha_fin: %s, "
							+ "ntfc_fecha_inicio: %s, " + "ntfc_importancia: %s " + "]",
					img.getId(), img.getEstado(), img.getFechaFin(), img.getFechaInicio(), img.getImportancia());

			logTO.setFechaAccion(LocalDateTime.now());
			logTO.setAccion(accion);

			Usuario usuario = this.iUsuarioRepository.buscarPorId(idAdmin);

			logTO.setUsuario(usuario);
			usuario.setLogs(null);

			this.iLogService.insertar(logTO);

			img.setUsuario(usuario);

			if (img.getEstado().equals("A")) {
				img.setEstado("I");
			} else if (img.getEstado().equals("I")) {
				img.setEstado("A");
			}

			this.iNotificacionRepo.actualizar(img);

			System.out.println("SE ACTUALIZÓ");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

}