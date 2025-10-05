package com.openfav.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.dto.ProyectoRequest;
import com.openfav.model.Proyecto;
import com.openfav.service.ProyectoService;

@RestController
@RequestMapping("/api/projects")
public class ProyectoController {

    private final ProyectoService proyectoService;

    public ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    @GetMapping("/{proyectoId}")
    public ResponseEntity<ProyectoDetalleDto> obtenerDetalle(@PathVariable Integer proyectoId) {
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    @PutMapping("/{proyectoId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarProyecto(@PathVariable Integer proyectoId,
            @RequestBody ProyectoRequest request) {
        Proyecto actualizado = proyectoService.actualizarProyecto(proyectoId, request);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(actualizado.getId()));
    }

    @DeleteMapping("/{proyectoId}")
    public ResponseEntity<Void> eliminarProyecto(@PathVariable Integer proyectoId) {
        proyectoService.eliminarProyecto(proyectoId);
        return ResponseEntity.noContent().build();
    }
}
