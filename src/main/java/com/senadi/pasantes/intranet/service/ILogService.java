package com.senadi.pasantes.intranet.service;

import com.senadi.pasantes.intranet.service.to.LogTO;

public interface ILogService {

	public void insertar(LogTO logTO);

	public LogTO buscarPorId(Integer id);

	public void actualizar(LogTO logTO);

	public void eliminar(Integer id);
	


}
