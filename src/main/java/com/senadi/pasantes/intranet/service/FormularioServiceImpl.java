package com.senadi.pasantes.intranet.service;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senadi.pasantes.intranet.repository.IFormularioRepository;
import com.senadi.pasantes.intranet.repository.modelo.Formulario;
import com.senadi.pasantes.intranet.service.to.FormularioTO;

@Service
public class FormularioServiceImpl implements IFormularioService {

	private final ModelMapper modelMapper = new ModelMapper();
	private final ObjectMapper objectMapper = new ObjectMapper();

	private FormularioTO convertirATO(Formulario formulario) {
		return modelMapper.map(formulario, FormularioTO.class);
	}

	private Formulario convertirAFormulario(FormularioTO formularioTO) {
		return modelMapper.map(formularioTO, Formulario.class);
	}

	@Autowired
	private IFormularioRepository iFormularioRepo;

	@Override
	public void insertar(FormularioTO formularioTO) throws JsonProcessingException {
		 // Convertir el Map en una cadena JSON
        String jsonContenido = objectMapper.writeValueAsString(formularioTO.getContenido());
        
        System.out.println("Contenido FormularioTO: "+formularioTO.getContenido());
        
        Formulario formulario= new Formulario();
        formulario.setEmisor(formularioTO.getEmisor());
        formulario.setEstado(formularioTO.getEstado());
        formulario.setFechaTratamiento(formularioTO.getFechaTratamiento());
        formulario.setFechaEmision(formularioTO.getFechaEmision());
        formulario.setIdDestinatario(formularioTO.getIdDestinatario());
        formulario.setContenido(jsonContenido);
        //formulario.setContenido(formularioTO.getContenido());
        
        this.iFormularioRepo.insertar(formulario);
        
	}

	@Override
	public FormularioTO buscarPorId(Integer id) throws JsonProcessingException {
		Formulario formulario=this.iFormularioRepo.buscarPorId(id);
		if (formulario == null) {
	        return null;  // Maneja el caso en que no se encuentra el formulario
	    }
	    
	    // Convertir el JSON almacenado en la columna 'contenido' a un Map o la estructura deseada
	    Map<String, Object> contenidoMap = objectMapper.readValue(formulario.getContenido(), new TypeReference<Map<String, Object>>() {});

	    // Crear el objeto FormularioTO a partir del resultado
	    FormularioTO formularioTO = new FormularioTO();
	    formularioTO.setEmisor(formulario.getEmisor());
	    formularioTO.setEstado(formulario.getEstado());
	    formularioTO.setFechaTratamiento(formulario.getFechaTratamiento());
	    formularioTO.setFechaEmision(formulario.getFechaEmision());
	    formularioTO.setIdDestinatario(formulario.getIdDestinatario());
	    formularioTO.setContenido(contenidoMap);  // Asignar el contenido convertido
	    //formularioTO.setContenido(formulario.getContenido());
	    return formularioTO;
	}

	@Override
	public void actualizar(FormularioTO formularioTO) {
		this.iFormularioRepo.actualizar(this.convertirAFormulario(formularioTO));
	}

	@Override
	public void eliminar(Integer id) {
		this.iFormularioRepo.eliminar(id);
	}
	
	
	//SERVICIOS PARA FormularioTO_HATEOAS 
	
	@Override
	public void insertarHATEOAS(FormularioTO formularioTO) throws JsonProcessingException {
		 // Convertir el Map en una cadena JSON
        String jsonContenido = objectMapper.writeValueAsString(formularioTO.getContenido());
        
        System.out.println("asdfasdfsadfsafsfasfsafsadf: "+formularioTO.getContenido());
        
        Formulario formulario= new Formulario();
        formulario.setEmisor(formularioTO.getEmisor());
        formulario.setEstado(formularioTO.getEstado());
        formulario.setFechaTratamiento(formularioTO.getFechaTratamiento());
        formulario.setFechaEmision(formularioTO.getFechaEmision());
        formulario.setIdDestinatario(formularioTO.getIdDestinatario());
        formulario.setContenido(jsonContenido);
        //formulario.setContenido(formularioTO.getContenido());
        
        this.iFormularioRepo.insertar(formulario);
        
	}

	@Override
	public FormularioTO buscarPorIdHATEOAS(Integer id) throws JsonProcessingException {
		Formulario formulario=this.iFormularioRepo.buscarPorId(id);
		if (formulario == null) {
	        return null;  // Maneja el caso en que no se encuentra el formulario
	    }
	    
	    // Convertir el JSON almacenado en la columna 'contenido' a un Map o la estructura deseada
	    Map<String, Object> contenidoMap = objectMapper.readValue(formulario.getContenido(), new TypeReference<Map<String, Object>>() {});

	    // Crear el objeto FormularioTO a partir del resultado
	    FormularioTO formularioTO = new FormularioTO();
	    formularioTO.setEmisor(formulario.getEmisor());
	    formularioTO.setEstado(formulario.getEstado());
	    formularioTO.setFechaTratamiento(formulario.getFechaTratamiento());
	    formularioTO.setFechaEmision(formulario.getFechaEmision());
	    formularioTO.setIdDestinatario(formulario.getIdDestinatario());
	    formularioTO.setContenido(contenidoMap);  // Asignar el contenido convertido
	    //formularioTO.setContenido(formulario.getContenido());

	    
	    return formularioTO;
	}
	
	@Override
	public void actualizarHATEOAS(FormularioTO formularioTO) {
		this.iFormularioRepo.actualizar(this.convertirAFormulario(formularioTO));
	}
	
	@Override
	public void eliminarHATEOAS(Integer id) {
		this.iFormularioRepo.eliminar(id);
	}
	
	
}