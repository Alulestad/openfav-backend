package com.openfav.backend.controller;

import com.openfav.backend.service.OngService;
import com.openfav.backend.service.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ong")
@Validated
public class OngController {

    private final OngService ongService;

    public OngController(OngService ongService) {
        this.ongService = ongService;
    }

    @GetMapping("/profile")
    public ResponseEntity<OngProfileDto> getProfile(Authentication authentication) {
        return ResponseEntity.ok(ongService.getProfile(authentication.getName()));
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectSummaryDto>> listProjects(Authentication authentication) {
        return ResponseEntity.ok(ongService.listProjects(authentication.getName()));
    }

    @PostMapping("/projects")
    public ResponseEntity<ProjectDetailDto> createProject(Authentication authentication,
                                                          @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(ongService.createProject(authentication.getName(), request));
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDetailDto> getProject(Authentication authentication,
                                                       @PathVariable Long projectId) {
        return ResponseEntity.ok(ongService.getProjectDetail(projectId, authentication.getName()));
    }

    @PutMapping("/projects/{projectId}")
    public ResponseEntity<ProjectDetailDto> updateProject(Authentication authentication,
                                                          @PathVariable Long projectId,
                                                          @Valid @RequestBody ProjectRequest request) {
        return ResponseEntity.ok(ongService.updateProject(projectId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}")
    public ResponseEntity<Void> deleteProject(Authentication authentication, @PathVariable Long projectId) {
        ongService.deleteProject(projectId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{projectId}/objectives")
    public ResponseEntity<ObjetivoEspecificoDto> addObjective(Authentication authentication,
                                                              @PathVariable Long projectId,
                                                              @Valid @RequestBody ObjetivoEspecificoRequest request) {
        return ResponseEntity.ok(ongService.addObjective(projectId, authentication.getName(), request));
    }

    @GetMapping("/projects/{projectId}/objectives/{objectiveId}")
    public ResponseEntity<ObjetivoEspecificoDto> getObjective(Authentication authentication,
                                                              @PathVariable Long projectId,
                                                              @PathVariable Long objectiveId) {
        return ResponseEntity.ok(ongService.getObjectiveDetail(projectId, objectiveId, authentication.getName()));
    }

    @PutMapping("/projects/{projectId}/objectives/{objectiveId}")
    public ResponseEntity<ObjetivoEspecificoDto> updateObjective(Authentication authentication,
                                                                 @PathVariable Long projectId,
                                                                 @PathVariable Long objectiveId,
                                                                 @Valid @RequestBody ObjetivoEspecificoRequest request) {
        return ResponseEntity.ok(ongService.updateObjective(projectId, objectiveId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}/objectives/{objectiveId}")
    public ResponseEntity<Void> deleteObjective(Authentication authentication,
                                                @PathVariable Long projectId,
                                                @PathVariable Long objectiveId) {
        ongService.deleteObjective(projectId, objectiveId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{projectId}/objectives/{objectiveId}/activities")
    public ResponseEntity<ActivityDto> addActivity(Authentication authentication,
                                                   @PathVariable Long projectId,
                                                   @PathVariable Long objectiveId,
                                                   @Valid @RequestBody ActividadRequest request) {
        return ResponseEntity.ok(ongService.addActivity(projectId, objectiveId, authentication.getName(), request));
    }

    @GetMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}")
    public ResponseEntity<ActivityDto> getActivity(Authentication authentication,
                                                   @PathVariable Long projectId,
                                                   @PathVariable Long objectiveId,
                                                   @PathVariable Long activityId) {
        return ResponseEntity.ok(ongService.getActivityDetail(projectId, objectiveId, activityId, authentication.getName()));
    }

    @PutMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}")
    public ResponseEntity<ActivityDto> updateActivity(Authentication authentication,
                                                      @PathVariable Long projectId,
                                                      @PathVariable Long objectiveId,
                                                      @PathVariable Long activityId,
                                                      @Valid @RequestBody ActividadRequest request) {
        return ResponseEntity.ok(ongService.updateActivity(projectId, objectiveId, activityId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}")
    public ResponseEntity<Void> deleteActivity(Authentication authentication,
                                               @PathVariable Long projectId,
                                               @PathVariable Long objectiveId,
                                               @PathVariable Long activityId) {
        ongService.deleteActivity(projectId, objectiveId, activityId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}/schedules")
    public ResponseEntity<ActivityScheduleDto> addSchedule(Authentication authentication,
                                                           @PathVariable Long projectId,
                                                           @PathVariable Long objectiveId,
                                                           @PathVariable Long activityId,
                                                           @Valid @RequestBody ActivityScheduleRequest request) {
        return ResponseEntity.ok(ongService.addSchedule(projectId, objectiveId, activityId, authentication.getName(), request));
    }

    @PutMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}/schedules/{scheduleId}")
    public ResponseEntity<ActivityScheduleDto> updateSchedule(Authentication authentication,
                                                              @PathVariable Long projectId,
                                                              @PathVariable Long objectiveId,
                                                              @PathVariable Long activityId,
                                                              @PathVariable Long scheduleId,
                                                              @Valid @RequestBody ActivityScheduleRequest request) {
        return ResponseEntity.ok(ongService.updateSchedule(projectId, objectiveId, activityId, scheduleId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(Authentication authentication,
                                               @PathVariable Long projectId,
                                               @PathVariable Long objectiveId,
                                               @PathVariable Long activityId,
                                               @PathVariable Long scheduleId) {
        ongService.deleteSchedule(projectId, objectiveId, activityId, scheduleId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}/kpis")
    public ResponseEntity<KpiDto> addKpi(Authentication authentication,
                                         @PathVariable Long projectId,
                                         @PathVariable Long objectiveId,
                                         @PathVariable Long activityId,
                                         @Valid @RequestBody KpiRequest request) {
        return ResponseEntity.ok(ongService.addKpi(projectId, objectiveId, activityId, authentication.getName(), request));
    }

    @PutMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}/kpis/{kpiId}")
    public ResponseEntity<KpiDto> updateKpi(Authentication authentication,
                                            @PathVariable Long projectId,
                                            @PathVariable Long objectiveId,
                                            @PathVariable Long activityId,
                                            @PathVariable Long kpiId,
                                            @Valid @RequestBody KpiRequest request) {
        return ResponseEntity.ok(ongService.updateKpi(projectId, objectiveId, activityId, kpiId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}/objectives/{objectiveId}/activities/{activityId}/kpis/{kpiId}")
    public ResponseEntity<Void> deleteKpi(Authentication authentication,
                                          @PathVariable Long projectId,
                                          @PathVariable Long objectiveId,
                                          @PathVariable Long activityId,
                                          @PathVariable Long kpiId) {
        ongService.deleteKpi(projectId, objectiveId, activityId, kpiId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{projectId}/budgets")
    public ResponseEntity<PresupuestoDto> addBudget(Authentication authentication,
                                                    @PathVariable Long projectId,
                                                    @Valid @RequestBody PresupuestoRequest request) {
        return ResponseEntity.ok(ongService.addBudget(projectId, authentication.getName(), request));
    }

    @GetMapping("/projects/{projectId}/budgets/{budgetId}")
    public ResponseEntity<PresupuestoDto> getBudget(Authentication authentication,
                                                    @PathVariable Long projectId,
                                                    @PathVariable Long budgetId) {
        return ResponseEntity.ok(ongService.getBudgetDetail(projectId, budgetId, authentication.getName()));
    }

    @PutMapping("/projects/{projectId}/budgets/{budgetId}")
    public ResponseEntity<PresupuestoDto> updateBudget(Authentication authentication,
                                                       @PathVariable Long projectId,
                                                       @PathVariable Long budgetId,
                                                       @Valid @RequestBody PresupuestoRequest request) {
        return ResponseEntity.ok(ongService.updateBudget(projectId, budgetId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}/budgets/{budgetId}")
    public ResponseEntity<Void> deleteBudget(Authentication authentication,
                                             @PathVariable Long projectId,
                                             @PathVariable Long budgetId) {
        ongService.deleteBudget(projectId, budgetId, authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/projects/{projectId}/budgets/{budgetId}/requests")
    public ResponseEntity<SolicitudDesembolsoDto> addBudgetRequest(Authentication authentication,
                                                                   @PathVariable Long projectId,
                                                                   @PathVariable Long budgetId,
                                                                   @Valid @RequestBody SolicitudDesembolsoRequest request) {
        return ResponseEntity.ok(ongService.addBudgetRequest(projectId, budgetId, authentication.getName(), request));
    }

    @PutMapping("/projects/{projectId}/budgets/{budgetId}/requests/{requestId}")
    public ResponseEntity<SolicitudDesembolsoDto> updateBudgetRequest(Authentication authentication,
                                                                      @PathVariable Long projectId,
                                                                      @PathVariable Long budgetId,
                                                                      @PathVariable Long requestId,
                                                                      @Valid @RequestBody SolicitudDesembolsoRequest request) {
        return ResponseEntity.ok(ongService.updateBudgetRequest(projectId, budgetId, requestId, authentication.getName(), request));
    }

    @DeleteMapping("/projects/{projectId}/budgets/{budgetId}/requests/{requestId}")
    public ResponseEntity<Void> deleteBudgetRequest(Authentication authentication,
                                                    @PathVariable Long projectId,
                                                    @PathVariable Long budgetId,
                                                    @PathVariable Long requestId) {
        ongService.deleteBudgetRequest(projectId, budgetId, requestId, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
