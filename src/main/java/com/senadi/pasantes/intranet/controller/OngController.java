package com.senadi.pasantes.intranet.controller;

import com.senadi.pasantes.intranet.model.Ong;
import com.senadi.pasantes.intranet.model.Proyecto;
import com.senadi.pasantes.intranet.service.OngService;
import com.senadi.pasantes.intranet.service.ProyectoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ongs")
@CrossOrigin(origins = "*")
public class OngController {

    private final OngService ongService;
    private final ProyectoService proyectoService;

    public OngController(OngService ongService, ProyectoService proyectoService) {
        this.ongService = ongService;
        this.proyectoService = proyectoService;
    }

    @GetMapping
    public ResponseEntity<List<Ong>> findAll() {
        return ResponseEntity.ok(ongService.findAll());
    }

    @PostMapping
    public ResponseEntity<Ong> create(@RequestBody Ong ong) {
        return ResponseEntity.ok(ongService.save(ong));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ong> findById(@PathVariable Long id) {
        return ResponseEntity.ok(ongService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ong> update(@PathVariable Long id, @RequestBody Ong ong) {
        ong.setId(id);
        return ResponseEntity.ok(ongService.save(ong));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ongService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<Proyecto>> findProjects(@PathVariable Long id) {
        return ResponseEntity.ok(proyectoService.findByOng(id));
    }

    @PostMapping("/{id}/projects")
    public ResponseEntity<Proyecto> createProject(@PathVariable Long id, @RequestBody Proyecto proyecto) {
        return ResponseEntity.ok(proyectoService.save(id, proyecto));
    }
}
