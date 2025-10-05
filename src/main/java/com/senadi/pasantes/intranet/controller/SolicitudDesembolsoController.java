package com.senadi.pasantes.intranet.controller;

import com.senadi.pasantes.intranet.model.SolicitudDesembolso;
import com.senadi.pasantes.intranet.service.SolicitudDesembolsoService;
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
@RequestMapping("/api/budget-requests")
@CrossOrigin(origins = "*")
public class SolicitudDesembolsoController {

    private final SolicitudDesembolsoService solicitudService;

    public SolicitudDesembolsoController(SolicitudDesembolsoService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<SolicitudDesembolso>> findByProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(solicitudService.findByProyecto(projectId));
    }

    @PostMapping("/project/{projectId}")
    public ResponseEntity<SolicitudDesembolso> create(@PathVariable("projectId") Long projectId,
                                                      @RequestBody SolicitudDesembolso solicitud) {
        return ResponseEntity.ok(solicitudService.save(projectId, solicitud));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDesembolso> update(@PathVariable Long id,
                                                      @RequestBody SolicitudDesembolso solicitud) {
        return ResponseEntity.ok(solicitudService.update(id, solicitud));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        solicitudService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
