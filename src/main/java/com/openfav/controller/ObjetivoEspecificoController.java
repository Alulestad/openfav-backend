package com.openfav.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfav.dto.ObjetivoEspecificoRequest;
import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.model.ObjetivoEspecifico;
import com.openfav.service.ObjetivoEspecificoService;
import com.openfav.service.ProyectoService;

@RestController
@RequestMapping("/api")
public class ObjetivoEspecificoController {

    private final ObjetivoEspecificoService objetivoService;
    private final ProyectoService proyectoService;

    public ObjetivoEspecificoController(ObjetivoEspecificoService objetivoService, ProyectoService proyectoService) {
        this.objetivoService = objetivoService;
        this.proyectoService = proyectoService;
    }

    @PostMapping("/projects/{proyectoId}/objectives")
    public ResponseEntity<ProyectoDetalleDto> crearObjetivo(@PathVariable Integer proyectoId,
            @RequestBody ObjetivoEspecificoRequest request) {
        ObjetivoEspecifico objetivo = objetivoService.crear(proyectoId, request);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(objetivo.getProyecto().getId()));
    }

    @PutMapping("/objectives/{objetivoId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarObjetivo(@PathVariable Integer objetivoId,
            @RequestBody ObjetivoEspecificoRequest request) {
        ObjetivoEspecifico objetivo = objetivoService.actualizar(objetivoId, request);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(objetivo.getProyecto().getId()));
    }

    @DeleteMapping("/objectives/{objetivoId}")
    public ResponseEntity<ProyectoDetalleDto> eliminarObjetivo(@PathVariable Integer objetivoId) {
        ObjetivoEspecifico objetivo = objetivoService.obtener(objetivoId);
        Integer proyectoId = objetivo.getProyecto().getId();
        objetivoService.eliminar(objetivoId);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }
}
