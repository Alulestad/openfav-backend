package com.senadi.pasantes.intranet.repository;

import java.util.List;

import com.senadi.pasantes.intranet.repository.modelo.Documento;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoArchivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoFormularioDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoInstructivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoListaDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoNormativaDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.FormatoPlantillaDTO;

public interface IDocumentoRepository {

	public void insertar(Documento documento);

	public Documento buscarPorId(Integer id);

	public List<DocumentoDTO> consultarTodoDTO();

	public DocumentoArchivoDTO buscarPorIdSoloDocumento(Integer id);

	public Integer actualizar(Documento documento);

	public Integer eliminar(Integer id);

	public List<DocumentoListaDTO> consultarTodosDocumentoListaDTO();

	public List<DocumentoNormativaDTO> consultarNormativasDTO();

	public List<DocumentoInstructivoDTO> consultarInstructivosDTO();
	
	public  List<FormatoPlantillaDTO> consultarFormatoPlantillaDTO();
	
	public List<DocumentoFormularioDTO> consultarFormulariosDTO();

}
