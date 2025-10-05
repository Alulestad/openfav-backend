package com.openfav.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openfav.dto.PresupuestoItemDto;
import com.openfav.dto.PresupuestoRequest;
import com.openfav.dto.ProyectoDetalleDto;
import com.openfav.model.Presupuesto;
import com.openfav.service.PresupuestoService;
import com.openfav.service.ProyectoService;

@RestController
@RequestMapping("/api")
public class PresupuestoController {

    private final PresupuestoService presupuestoService;
    private final ProyectoService proyectoService;

    public PresupuestoController(PresupuestoService presupuestoService, ProyectoService proyectoService) {
        this.presupuestoService = presupuestoService;
        this.proyectoService = proyectoService;
    }

    @GetMapping("/projects/{proyectoId}/budgets")
    public ResponseEntity<List<PresupuestoItemDto>> listarPresupuestos(@PathVariable Integer proyectoId) {
        List<Presupuesto> presupuestos = presupuestoService.listarPorProyecto(proyectoId);
        List<PresupuestoItemDto> respuesta = presupuestos.stream()
                .map(this::mapPresupuesto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping("/projects/{proyectoId}/budgets")
    public ResponseEntity<ProyectoDetalleDto> crearPresupuesto(@PathVariable Integer proyectoId,
            @RequestBody PresupuestoRequest request) {
        Presupuesto presupuesto = presupuestoService.crear(proyectoId, request);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(presupuesto.getProyecto().getId()));
    }

    @PutMapping("/budgets/{presupuestoId}")
    public ResponseEntity<ProyectoDetalleDto> actualizarPresupuesto(@PathVariable Integer presupuestoId,
            @RequestBody PresupuestoRequest request) {
        Presupuesto presupuesto = presupuestoService.actualizar(presupuestoId, request);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(presupuesto.getProyecto().getId()));
    }

    @DeleteMapping("/budgets/{presupuestoId}")
    public ResponseEntity<ProyectoDetalleDto> eliminarPresupuesto(@PathVariable Integer presupuestoId) {
        Presupuesto presupuesto = presupuestoService.obtener(presupuestoId);
        Integer proyectoId = presupuesto.getProyecto().getId();
        presupuestoService.eliminar(presupuestoId);
        return ResponseEntity.ok(proyectoService.obtenerDetalle(proyectoId));
    }

    private PresupuestoItemDto mapPresupuesto(Presupuesto presupuesto) {
        PresupuestoItemDto dto = new PresupuestoItemDto();
        dto.setId(presupuesto.getId());
        dto.setCategoria(presupuesto.getCategoria());
        dto.setCantidad(presupuesto.getCantidad());
        dto.setUnidades(presupuesto.getUnidades());
        dto.setValor(presupuesto.getValor());
        return dto;
    }
}
