package com.prismatech.prismaproject.controller;

import com.prismatech.prismaproject.dto.ProyectoDto;
import com.prismatech.prismaproject.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
public class ProyectoController {
    private final ProyectoService service;

    public ProyectoController(ProyectoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProyectoDto> create(@RequestBody ProyectoDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProyectoDto>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoDto> update(@PathVariable Integer id, @RequestBody ProyectoDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
