package com.senadi.pasantes.intranet.controller;

import com.senadi.pasantes.intranet.model.PresupuestoItem;
import com.senadi.pasantes.intranet.service.PresupuestoService;
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
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class PresupuestoController {

    private final PresupuestoService presupuestoService;

    public PresupuestoController(PresupuestoService presupuestoService) {
        this.presupuestoService = presupuestoService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<PresupuestoItem>> findByProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(presupuestoService.findByProyecto(projectId));
    }

    @PostMapping("/project/{projectId}")
    public ResponseEntity<PresupuestoItem> create(@PathVariable("projectId") Long projectId,
                                                  @RequestBody PresupuestoItem item) {
        return ResponseEntity.ok(presupuestoService.save(projectId, item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PresupuestoItem> update(@PathVariable Long id, @RequestBody PresupuestoItem item) {
        return ResponseEntity.ok(presupuestoService.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        presupuestoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
