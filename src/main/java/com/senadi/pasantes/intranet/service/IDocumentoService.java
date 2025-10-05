package com.senadi.pasantes.intranet.service;

import java.util.List;

import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoArchivoDTO;
import com.senadi.pasantes.intranet.service.to.DocumentoDTO_TO;
import com.senadi.pasantes.intranet.service.to.DocumentoFormularioTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.FormatoPlantillaDTO;
import com.senadi.pasantes.intranet.service.to.DocumentoInstructivoTO;
import com.senadi.pasantes.intranet.service.to.DocumentoListaTO;
import com.senadi.pasantes.intranet.service.to.DocumentoNormativaTO;
import com.senadi.pasantes.intranet.service.to.DocumentoTO;
import com.senadi.pasantes.intranet.service.to.FormatoPlantillaTO;

public interface IDocumentoService {

	public void insertar(DocumentoTO documentoTO);

	public DocumentoTO buscarPorId(Integer id);

	public List<DocumentoDTO_TO> consultarTodoDTO();

	public DocumentoArchivoDTO buscarPorIdSoloDocumento(Integer id);

	public Integer actualizar(DocumentoTO documentoTO);

	public Integer cambiarEstado(Integer id, Integer idAdmin);

	public Integer eliminar(Integer id);

	public List<DocumentoListaTO> buscarTodosDocumentoListaTO();

	public List<DocumentoNormativaTO> buscarNormativasTO();

	public List<DocumentoInstructivoTO> buscarInstructivosTO();
	
	public  List<FormatoPlantillaTO> buscarFormatoPlantillaTO();

	 public List<DocumentoFormularioTO> buscarFormulariosTO();

}
