package com.senadi.pasantes.intranet.repository;

import com.senadi.pasantes.intranet.repository.modelo.Log;

public interface ILogRepository {

	public void insertar(Log log);

	public Log buscarPorId(Integer id);

	public void actualizar(Log log);

	public void eliminar(Integer id);

}
