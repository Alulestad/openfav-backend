package com.openfav.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfav.dto.OngProfileDto;
import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.dto.ProyectoRequest;
import com.openfav.dto.ProyectoResumenDto;
import com.openfav.model.Proyecto;
import com.openfav.service.OngService;
import com.openfav.service.ProyectoService;

@RestController
@RequestMapping("/api/ongs")
public class OngController {

    private final OngService ongService;
    private final ProyectoService proyectoService;

    public OngController(OngService ongService, ProyectoService proyectoService) {
        this.ongService = ongService;
        this.proyectoService = proyectoService;
    }

    @GetMapping("/{ongId}")
    public ResponseEntity<OngProfileDto> obtenerPerfil(@PathVariable Integer ongId) {
        return ResponseEntity.ok(ongService.getPerfil(ongId));
    }

    @GetMapping("/{ongId}/projects")
    public ResponseEntity<List<ProyectoResumenDto>> listarProyectos(@PathVariable Integer ongId) {
        return ResponseEntity.ok(ongService.listarProyectos(ongId));
    }

    @PostMapping("/{ongId}/projects")
    public ResponseEntity<ProyectoDetalleDto> crearProyecto(@PathVariable Integer ongId,
            @RequestBody ProyectoRequest request) {
        Proyecto nuevo = proyectoService.crearProyecto(ongId, request);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(nuevo.getId()));
    }
}
