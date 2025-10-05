package com.openfav.backend.controller;

import com.openfav.backend.service.FavoritaService;
import com.openfav.backend.service.dto.OngProfileDto;
import com.openfav.backend.service.dto.ProjectDetailDto;
import com.openfav.backend.service.dto.ProjectSummaryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favorita")
public class FavoritaController {

    private final FavoritaService favoritaService;

    public FavoritaController(FavoritaService favoritaService) {
        this.favoritaService = favoritaService;
    }

    @GetMapping("/ongs")
    public ResponseEntity<List<OngProfileDto>> listOngs() {
        return ResponseEntity.ok(favoritaService.listOngs());
    }

    @GetMapping("/ongs/{ongId}/projects")
    public ResponseEntity<List<ProjectSummaryDto>> listProjects(@PathVariable Long ongId) {
        return ResponseEntity.ok(favoritaService.listProjectsByOng(ongId));
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDetailDto> getProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(favoritaService.getProjectDetail(projectId));
    }
}
