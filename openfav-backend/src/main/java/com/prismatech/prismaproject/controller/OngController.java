package com.prismatech.prismaproject.controller;

import com.prismatech.prismaproject.model.Ong;
import com.prismatech.prismaproject.service.OngService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ongs")
@CrossOrigin(origins = "*")
public class OngController {
    private final OngService service;

    public OngController(OngService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Ong> create(@RequestBody Ong ong) {
        return ResponseEntity.ok(service.create(ong));
    }

    @GetMapping
    public ResponseEntity<List<Ong>> all() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ong> get(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ong> update(@PathVariable Integer id, @RequestBody Ong ong) {
        return ResponseEntity.ok(service.update(id, ong));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
