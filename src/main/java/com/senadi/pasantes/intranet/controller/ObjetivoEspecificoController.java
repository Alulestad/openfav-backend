package com.senadi.pasantes.intranet.controller;

import com.senadi.pasantes.intranet.model.ObjetivoEspecifico;
import com.senadi.pasantes.intranet.service.ObjetivoEspecificoService;
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
@RequestMapping("/api/objectives")
@CrossOrigin(origins = "*")
public class ObjetivoEspecificoController {

    private final ObjetivoEspecificoService objetivoService;

    public ObjetivoEspecificoController(ObjetivoEspecificoService objetivoService) {
        this.objetivoService = objetivoService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<ObjetivoEspecifico>> findByProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok(objetivoService.findByProyecto(projectId));
    }

    @PostMapping("/project/{projectId}")
    public ResponseEntity<ObjetivoEspecifico> create(@PathVariable("projectId") Long projectId,
                                                     @RequestBody ObjetivoEspecifico objetivo) {
        return ResponseEntity.ok(objetivoService.save(projectId, objetivo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjetivoEspecifico> update(@PathVariable Long id, @RequestBody ObjetivoEspecifico objetivo) {
        return ResponseEntity.ok(objetivoService.update(id, objetivo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        objetivoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
