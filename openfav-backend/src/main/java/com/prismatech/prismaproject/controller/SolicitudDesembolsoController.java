package com.prismatech.prismaproject.controller;

import com.prismatech.prismaproject.dto.SolicitudDesembolsoDto;
import com.prismatech.prismaproject.service.SolicitudDesembolsoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudDesembolsoController {

    private final SolicitudDesembolsoService service;

    public SolicitudDesembolsoController(SolicitudDesembolsoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SolicitudDesembolsoDto> create(@RequestBody SolicitudDesembolsoDto dto) {
        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDesembolsoDto>> all(
            @RequestParam(name = "idPres", required = false) Integer idPres,
            @RequestParam(name = "idProy", required = false) Integer idProy) {
        if (idPres != null) {
            return ResponseEntity.ok(service.getByPresupuesto(idPres));
        }
        if (idProy != null) {
            return ResponseEntity.ok(service.getByProyecto(idProy));
        }
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDesembolsoDto> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDesembolsoDto> update(@PathVariable Integer id, @RequestBody SolicitudDesembolsoDto dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
