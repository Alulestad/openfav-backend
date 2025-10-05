package com.prismatech.prismaproject.controller;

import com.prismatech.prismaproject.dto.PresupuestoDto;
import com.prismatech.prismaproject.service.PresupuestoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/presupuestos")
@CrossOrigin(origins = "*")
public class PresupuestoController {

    private final PresupuestoService service;

    public PresupuestoController(PresupuestoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PresupuestoDto> create(@RequestBody PresupuestoDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<PresupuestoDto>> all(@RequestParam(name = "idProy", required = false) Integer idProy) {
        if (idProy != null) {
            return ResponseEntity.ok(service.getByProyecto(idProy));
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresupuestoDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PresupuestoDto> update(@PathVariable Integer id, @RequestBody PresupuestoDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
