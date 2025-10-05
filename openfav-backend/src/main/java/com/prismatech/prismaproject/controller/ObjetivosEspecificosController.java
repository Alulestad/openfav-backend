package com.prismatech.prismaproject.controller;

import com.prismatech.prismaproject.dto.ObjetivosEspecificosDto;
import com.prismatech.prismaproject.service.ObjetivosEspecificosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/objetivos")
@CrossOrigin(origins = "*")
public class ObjetivosEspecificosController {

    private final ObjetivosEspecificosService service;

    public ObjetivosEspecificosController(ObjetivosEspecificosService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ObjetivosEspecificosDto> create(@RequestBody ObjetivosEspecificosDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ObjetivosEspecificosDto>> all(@RequestParam(name = "idProy", required = false) Integer idProy) {
        if (idProy != null) {
            return ResponseEntity.ok(service.getByProyecto(idProy));
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ObjetivosEspecificosDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetivosEspecificosDto> update(@PathVariable Integer id, @RequestBody ObjetivosEspecificosDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
