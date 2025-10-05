package com.senadi.pasantes.intranet.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.senadi.pasantes.intranet.repository.modelo.Documento;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoArchivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoFormularioDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoInstructivoDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoListaDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoNormativaDTO;
import com.senadi.pasantes.intranet.repository.modelo.dto.FormatoPlantillaDTO;

@Repository
@Transactional
public class DocumentoRepositoryImpl implements IDocumentoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void insertar(Documento documento) {
		this.entityManager.persist(documento);
	}

	@Override
	public Documento buscarPorId(Integer id) {
		return this.entityManager.find(Documento.class, id);
	}

	@Override
	public List<DocumentoDTO> consultarTodoDTO() {
		Query q = this.entityManager.createQuery(
				"SELECT NEW com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoDTO(d.id, d.nombre, d.categoria, d.estado) FROM Documento d");
		return q.getResultList();
	}

	@Override
	public DocumentoArchivoDTO buscarPorIdSoloDocumento(Integer id) {
		Query q = this.entityManager.createQuery(
				"SELECT NEW com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoArchivoDTO(d.nombre, d.documento, d.tipo) FROM Documento d WHERE d.id = :id");
		q.setParameter("id", id);
		return (DocumentoArchivoDTO) q.getSingleResult();
	}

	@Override
	public Integer actualizar(Documento documento) {
		try {
			this.entityManager.merge(documento);
			return 1;
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public Integer eliminar(Integer id) {
		Documento doc = this.buscarPorId(id);
		System.out.println("dentro eliminar");
		try {
			this.entityManager.remove(doc);
			return 1;
		} catch (Exception e) {
			return 0;

		}
	}

	@Override
	public List<DocumentoListaDTO> consultarTodosDocumentoListaDTO() {
		TypedQuery<DocumentoListaDTO> myQuery = this.entityManager.createQuery(""
				+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoListaDTO(d.id, d.nombre, d.categoria, d.documento, d.documentoURL, d.tipo, d.estado) "
				+ "from Documento d", DocumentoListaDTO.class);

		return myQuery.getResultList();
	}

	@Override
	public List<DocumentoNormativaDTO> consultarNormativasDTO() {
		TypedQuery<DocumentoNormativaDTO> myQuery = this.entityManager.createQuery(""
				+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoNormativaDTO(d.id, d.nombre,d.fechaCreacion,d.fechaActualizacion, d.tipo) "
				+ "from Documento d " + "where d.estado='A' and  d.categoria='normativa'", DocumentoNormativaDTO.class);

		return myQuery.getResultList();
	}

	@Override
	public List<DocumentoInstructivoDTO> consultarInstructivosDTO() {
		TypedQuery<DocumentoInstructivoDTO> myQuery = this.entityManager.createQuery(""
				+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoInstructivoDTO(d.id, d.nombre,d.fechaCreacion,d.fechaActualizacion, d.tipo) "
				+ "from Documento d " + "where d.estado='A' and d.categoria='instructivo'",
				DocumentoInstructivoDTO.class);

		return myQuery.getResultList();
	}
	
	
	public  List<FormatoPlantillaDTO> consultarFormatoPlantillaDTO(){
		TypedQuery<FormatoPlantillaDTO> myQuery = this.entityManager.createQuery(""
				+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.FormatoPlantillaDTO(d.id, d.nombre,d.fechaCreacion,d.fechaActualizacion, d.documento, d.tipo, d.categoria) "
				+ "from Documento d " + "where d.estado='A' and (d.categoria='formato' or d.categoria='plantilla')",
				FormatoPlantillaDTO.class);

		return myQuery.getResultList();
	}
	
	
	
	@Override
	public List<DocumentoFormularioDTO> consultarFormulariosDTO() {
		TypedQuery<DocumentoFormularioDTO> myQuery = this.entityManager.createQuery(""
				+ "select new com.senadi.pasantes.intranet.repository.modelo.dto.DocumentoFormularioDTO(d.id, d.nombre,d.fechaCreacion,d.fechaActualizacion, d.tipo) "
				+ "from Documento d " + "where d.estado='A' and d.categoria='formulario'",
				DocumentoFormularioDTO.class);

		return myQuery.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
