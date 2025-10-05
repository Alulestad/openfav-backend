package com.prismatech.prismaproject.controller;

import com.prismatech.prismaproject.dto.ActividadDto;
import com.prismatech.prismaproject.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ActividadController {
    private final ActividadService service;

    public ActividadController(ActividadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ActividadDto> create(@RequestBody ActividadDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ActividadDto>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActividadDto> update(@PathVariable Integer id, @RequestBody ActividadDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
