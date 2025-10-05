package com.senadi.pasantes.intranet.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senadi.pasantes.intranet.repository.IDocumentoRepository;
import com.senadi.pasantes.intranet.repository.IUsuarioRepository;
import com.senadi.pasantes.intranet.repository.modelo.Documento;
import com.senadi.pasantes.intranet.repository.modelo.Usuario;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoArchivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoFormularioDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoInstructivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoListaDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoNormativaDTO;
import com.senadi.pasantes.intranet.service.to.DocumentoDTO_TO;
import com.senadi.pasantes.intranet.service.to.DocumentoFormularioTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.FormatoPlantillaDTO;
import com.senadi.pasantes.intranet.service.to.DocumentoInstructivoTO;
import com.senadi.pasantes.intranet.service.to.DocumentoListaTO;
import com.senadi.pasantes.intranet.service.to.DocumentoNormativaTO;
import com.senadi.pasantes.intranet.service.to.DocumentoTO;
import com.senadi.pasantes.intranet.service.to.FormatoPlantillaTO;
import com.senadi.pasantes.intranet.service.to.LogTO;

@Service
public class DocumentoServiceImpl implements IDocumentoService {

	private final ModelMapper modelMapper = new ModelMapper();

	private DocumentoTO convertirATO(Documento documento) {
		return modelMapper.map(documento, DocumentoTO.class);
	}

	private Documento convertirADocumento(DocumentoTO documentoTO) {
		return modelMapper.map(documentoTO, Documento.class);
	}

	private DocumentoDTO_TO convertirADocumentoDTO_TO(DocumentoDTO documentoDTO) {
		return modelMapper.map(documentoDTO, DocumentoDTO_TO.class);
	}

	private DocumentoListaTO documentoListaDTOToDocumentoListaTO(DocumentoListaDTO documentoListaDTO) {
		return modelMapper.map(documentoListaDTO, DocumentoListaTO.class);
	}

	private DocumentoListaDTO documentoListaTOToDocumentoListaDTO(DocumentoListaTO documentoListaTO) {
		return modelMapper.map(documentoListaTO, DocumentoListaDTO.class);
	}

	private DocumentoNormativaTO documentoNormativaDTOToDocumentoNormativaTO(
			DocumentoNormativaDTO documentoNormativaDTO) {
		return modelMapper.map(documentoNormativaDTO, DocumentoNormativaTO.class);
	}

	private DocumentoNormativaDTO documentoNormativaTOToDocumentoNormativaDTO(
			DocumentoNormativaTO documentoNormativaTO) {
		return modelMapper.map(documentoNormativaTO, DocumentoNormativaDTO.class);
	}

	private DocumentoInstructivoTO documentoInstructivoDTOToDocumentoInstructivoTO(
			DocumentoInstructivoDTO documentoInstructivoDTO) {
		return modelMapper.map(documentoInstructivoDTO, DocumentoInstructivoTO.class);
	}

	private DocumentoInstructivoDTO documentoInstructivoTOToDocumentoInstructivoDTO(
			DocumentoInstructivoTO documentoInstructivoTO) {
		return modelMapper.map(documentoInstructivoTO, DocumentoInstructivoDTO.class);
	}
	
	private FormatoPlantillaTO formatoPlantillaDTOToFormatoPlantillaTO(
			FormatoPlantillaDTO formatoPlantillaDTO) {
		return modelMapper.map(formatoPlantillaDTO, FormatoPlantillaTO.class);
	}

	private FormatoPlantillaDTO formatoPlantillaTOToFormatoPlantillaDTO(FormatoPlantillaTO formatoPlantillaTO) {
	   
	    return modelMapper.map(formatoPlantillaTO, FormatoPlantillaDTO.class);
	}
	
	private DocumentoFormularioTO documentoFormularioDTOToDocumentoFormularioTO(
			DocumentoFormularioDTO documentoFormularioDTO) {
		return modelMapper.map(documentoFormularioDTO, DocumentoFormularioTO.class);
	}

	private DocumentoFormularioDTO documentoFormularioTOToDocumentoFormularioDTO(
			DocumentoFormularioTO documentoFormularioTO) {
		return modelMapper.map(documentoFormularioTO, DocumentoFormularioDTO.class);
	}

	

	@Autowired
	private IDocumentoRepository iDocumentoRepo;

	@Autowired
	private ILogService iLogService;

	@Autowired
	private IUsuarioService iUsuarioService;

	@Autowired
	private IUsuarioRepository iUsuarioRepository;

	@Override
	public void insertar(DocumentoTO documentoTO) {
		System.out.println("docTO" + documentoTO);
		this.iDocumentoRepo.insertar(this.convertirADocumento(documentoTO));

		// LOGS
		LogTO logTO = new LogTO();
		String accion = String.format("Inserto: [docu_id: %s]", documentoTO.getId());

		logTO.setId(documentoTO.getId());
		logTO.setFechaAccion(LocalDateTime.now());
		logTO.setAccion(accion);
		this.iLogService.insertar(logTO);

	}

	@Override
	public DocumentoTO buscarPorId(Integer id) {
		return this.convertirATO(this.iDocumentoRepo.buscarPorId(id));
	}

	@Override
	public List<DocumentoDTO_TO> consultarTodoDTO() {
		List<DocumentoDTO> pdto = this.iDocumentoRepo.consultarTodoDTO();
		List<DocumentoDTO_TO> lsdto_to = new ArrayList<>();
		for (DocumentoDTO documentoDTO : pdto) {
			lsdto_to.add(this.convertirADocumentoDTO_TO(documentoDTO));
		}
		return lsdto_to;
	}

	@Override
	public DocumentoArchivoDTO buscarPorIdSoloDocumento(Integer id) {
		return this.iDocumentoRepo.buscarPorIdSoloDocumento(id);
	}

	@Override
	public Integer actualizar(DocumentoTO documentoTO) {
		// En ambos casos se respeta la fecha de creacion.
		// Si no se altera el documento y el tipo de documento:
		var docActualizar = this.iDocumentoRepo.buscarPorId(documentoTO.getId());
		docActualizar.setNombre(documentoTO.getNombre());
		docActualizar.setCategoria(documentoTO.getCategoria());
		docActualizar.setFechaActualizacion(documentoTO.getFechaActualizacion());
		docActualizar.setUsuario(documentoTO.getUsuario());

		// Si se coloca un nuevo documento:
		if (documentoTO.getDocumento() != null) {
			docActualizar.setDocumento(documentoTO.getDocumento());
			docActualizar.setTipo(documentoTO.getTipo());
			docActualizar.setEstado(documentoTO.getEstado());
		}

		Integer act = -1;
		try {
			// LOGS
			LogTO logTO = new LogTO();
			String accion = String.format(
					"Modifico a partir de: [docu_id: %s, " + "docu_categoria: %s, " + "docu_nombre: %s, "
							+ "docu_tipo: %s, " + "docu_fecha_actualizacion: %s]",
					documentoTO.getId(), documentoTO.getCategoria(), documentoTO.getNombre(), documentoTO.getTipo(),
					documentoTO.getFechaActualizacion());

			logTO.setFechaAccion(LocalDateTime.now());
			logTO.setAccion(accion);
			logTO.setUsuario(this.iUsuarioRepository.buscarPorId(documentoTO.getId()));

			this.iLogService.insertar(logTO);

			act = this.iDocumentoRepo.actualizar(docActualizar);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return act;

	}

	@Override
	public Integer eliminar(Integer id) {
		return this.iDocumentoRepo.eliminar(id);
	}

	@Override
	public Integer cambiarEstado(Integer id, Integer idAdmin) {
		Documento docu = this.iDocumentoRepo.buscarPorId(id);
		System.out.println("DocumentoServiceImpl>cambiarEstado>docu: " + docu);
		try {

			// LOGS
			LogTO logTO = new LogTO();
			String accion = String.format("Modifico a partir de: [docu_id: %s, " + "docu_estado: %s]", docu.getId(),
					docu.getEstado());

			logTO.setFechaAccion(LocalDateTime.now());
			logTO.setAccion(accion);

			Usuario usuario = this.iUsuarioRepository.buscarPorId(idAdmin);

			logTO.setUsuario(usuario);
			usuario.setLogs(null);

			this.iLogService.insertar(logTO);

			docu.setUsuario(usuario);

			if (docu.getEstado().equals("A")) {
				docu.setEstado("I");
			} else if (docu.getEstado().equals("I")) {
				docu.setEstado("A");
			}

			this.iDocumentoRepo.actualizar(docu);

			System.out.println("SE ACTUALIZAO");
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public List<DocumentoListaTO> buscarTodosDocumentoListaTO() {

		List<DocumentoListaDTO> documentoListaDTO = this.iDocumentoRepo.consultarTodosDocumentoListaDTO();
		List<DocumentoListaTO> documentoListaTO = new ArrayList<>();

		for (DocumentoListaDTO docsDTO : documentoListaDTO) {
			documentoListaTO.add(this.documentoListaDTOToDocumentoListaTO(docsDTO));
		}

		return documentoListaTO;
	}

	@Override
	public List<DocumentoNormativaTO> buscarNormativasTO() {
		List<DocumentoNormativaDTO> documentoNormativaDTOs = this.iDocumentoRepo.consultarNormativasDTO();
		List<DocumentoNormativaTO> documentoNormativaTOs = new ArrayList<>();

		for (DocumentoNormativaDTO normDTO : documentoNormativaDTOs) {
			documentoNormativaTOs.add(this.documentoNormativaDTOToDocumentoNormativaTO(normDTO));
		}

		return documentoNormativaTOs;
	}

	@Override
	public List<DocumentoInstructivoTO> buscarInstructivosTO() {
		List<DocumentoInstructivoDTO> documentoInstructivoDTOs = this.iDocumentoRepo.consultarInstructivosDTO();
		List<DocumentoInstructivoTO> documentoInstructivoTOs = new ArrayList<>();

		for (DocumentoInstructivoDTO normDTO : documentoInstructivoDTOs) {
			documentoInstructivoTOs.add(this.documentoInstructivoDTOToDocumentoInstructivoTO(normDTO));
		}

		return documentoInstructivoTOs;
	}

	@Override
	public List<FormatoPlantillaTO> buscarFormatoPlantillaTO() {
		// TODO Auto-generated method stub
		List<FormatoPlantillaDTO> formatoPlantillaDTOs= this.iDocumentoRepo.consultarFormatoPlantillaDTO();
		List<FormatoPlantillaTO> formatoPlantillaTOs= new ArrayList<>();
		
		for (FormatoPlantillaDTO formPlantDTO : formatoPlantillaDTOs) {
			formatoPlantillaTOs.add(this.formatoPlantillaDTOToFormatoPlantillaTO(formPlantDTO));
		}
		
		return formatoPlantillaTOs;
	}
	
	@Override
	public List<DocumentoFormularioTO> buscarFormulariosTO() {
		List<DocumentoFormularioDTO> documentoFormularioDTOs = this.iDocumentoRepo.consultarFormulariosDTO();
		List<DocumentoFormularioTO> documentoFormularioTOs = new ArrayList<>();

		for (DocumentoFormularioDTO normDTO : documentoFormularioDTOs) {
			documentoFormularioTOs.add(this.documentoFormularioDTOToDocumentoFormularioTO(normDTO));
		}

		return documentoFormularioTOs;
	}

}