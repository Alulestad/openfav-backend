package com.senadi.pasantes.intranet.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senadi.pasantes.intranet.repository.ILogRepository;
import com.senadi.pasantes.intranet.repository.modelo.Log;
import com.senadi.pasantes.intranet.service.to.LogTO;

@Service
public class LogServiceImpl implements ILogService {

	private final ModelMapper modelMapper = new ModelMapper();

	private LogTO convertirATO(Log log) {
		return modelMapper.map(log, LogTO.class);
	}

	private Log convertirALog(LogTO logTO) {
		return modelMapper.map(logTO, Log.class);
	}

	@Autowired
	private ILogRepository iLogRepo;

	@Override
	public void insertar(LogTO logTO) {
		this.iLogRepo.insertar(this.convertirALog(logTO));
	}

	@Override
	public LogTO buscarPorId(Integer id) {
		return this.convertirATO(this.iLogRepo.buscarPorId(id));
	}

	@Override
	public void actualizar(LogTO logTO) {
		this.iLogRepo.actualizar(this.convertirALog(logTO));
	}

	@Override
	public void eliminar(Integer id) {
		this.iLogRepo.eliminar(id);
	}

}