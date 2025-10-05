package com.senadi.pasantes.intranet.controller;

import com.senadi.pasantes.intranet.model.Actividad;
import com.senadi.pasantes.intranet.model.ActividadFecha;
import com.senadi.pasantes.intranet.model.Kpi;
import com.senadi.pasantes.intranet.service.ActividadService;
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
@RequestMapping("/api/activities")
@CrossOrigin(origins = "*")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @GetMapping("/objective/{objectiveId}")
    public ResponseEntity<List<Actividad>> findByObjective(@PathVariable("objectiveId") Long objectiveId) {
        return ResponseEntity.ok(actividadService.findByObjetivo(objectiveId));
    }

    @PostMapping("/objective/{objectiveId}")
    public ResponseEntity<Actividad> create(@PathVariable("objectiveId") Long objectiveId,
                                            @RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.save(objectiveId, actividad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Actividad> update(@PathVariable Long id, @RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.update(id, actividad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        actividadService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/schedules")
    public ResponseEntity<List<ActividadFecha>> findSchedules(@PathVariable Long id) {
        return ResponseEntity.ok(actividadService.findFechas(id));
    }

    @PostMapping("/{id}/schedules")
    public ResponseEntity<ActividadFecha> addSchedule(@PathVariable Long id, @RequestBody ActividadFecha fecha) {
        return ResponseEntity.ok(actividadService.addFecha(id, fecha));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
        actividadService.removeFecha(scheduleId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/kpis")
    public ResponseEntity<List<Kpi>> findKpis(@PathVariable Long id) {
        return ResponseEntity.ok(actividadService.findKpis(id));
    }

    @PostMapping("/{id}/kpis")
    public ResponseEntity<Kpi> addKpi(@PathVariable Long id, @RequestBody Kpi kpi) {
        return ResponseEntity.ok(actividadService.addKpi(id, kpi));
    }

    @DeleteMapping("/kpis/{kpiId}")
    public ResponseEntity<Void> deleteKpi(@PathVariable("kpiId") Long kpiId) {
        actividadService.removeKpi(kpiId);
        return ResponseEntity.noContent().build();
    }
}
