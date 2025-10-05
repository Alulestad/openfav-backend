import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ActivitiesService, ActividadDto } from '../../../core/services/activities.service';
import { ObjetivoDto, ObjetivosService } from '../../../core/services/objetivos.service';
import { ProjectsService, ProyectoDto } from '../../../core/services/projects.service';

@Component({
  selector: 'app-ong-activities',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './ong-activities.component.html',
  styleUrl: './ong-activities.component.scss'
})
export class OngActivitiesComponent {
  private readonly projectsService = inject(ProjectsService);
  private readonly objetivosService = inject(ObjetivosService);
  private readonly activitiesService = inject(ActivitiesService);

  projects: ProyectoDto[] = [];
  objectives: ObjetivoDto[] = [];
  activities: ActividadDto[] = [];

  selectedProjectId: number | null = null;
  selectedObjectiveId: number | null = null;
  selectedActivityId: number | null = null;

  objectiveForm: ObjetivoDto = this.emptyObjective();
  activityForm: ActividadDto = this.emptyActivity();

  ngOnInit() {
    this.projectsService.list().subscribe({ next: (projects) => (this.projects = projects) });
  }

  onProjectChange() {
    if (!this.selectedProjectId) {
      this.objectives = [];
      this.activities = [];
      return;
    }
    this.objetivosService.list(this.selectedProjectId).subscribe({
      next: (objectives) => {
        this.objectives = objectives;
        if (!objectives.some((o) => o.idObjesp === this.selectedObjectiveId)) {
          this.selectedObjectiveId = null;
          this.activities = [];
        } else {
          this.onObjectiveChange();
        }
      },
      error: (err) => console.error('No se pudieron cargar los objetivos', err)
    });
  }

  onObjectiveChange() {
    if (!this.selectedObjectiveId) {
      this.activities = [];
      return;
    }
    this.activitiesService.list(this.selectedObjectiveId).subscribe({
      next: (activities) => (this.activities = activities)
    });
  }

  editObjective(objective: ObjetivoDto) {
    this.selectedObjectiveId = objective.idObjesp ?? null;
    this.objectiveForm = { ...objective };
    this.onObjectiveChange();
  }

  saveObjective() {
    if (!this.selectedProjectId) {
      alert('Selecciona un proyecto');
      return;
    }
    const payload: ObjetivoDto = {
      ...this.objectiveForm,
      idProy: this.selectedProjectId
    };
    if (!payload.objetivoObjesp) {
      alert('Describe el objetivo');
      return;
    }
    const request = this.objectiveForm.idObjesp
      ? this.objetivosService.update(this.objectiveForm.idObjesp, payload)
      : this.objetivosService.create(payload);
    request.subscribe({
      next: (objective) => {
        this.objectiveForm = this.emptyObjective();
        this.selectedObjectiveId = objective.idObjesp ?? null;
        this.onProjectChange();
        if (this.selectedObjectiveId) {
          this.activitiesService.list(this.selectedObjectiveId).subscribe({
            next: (activities) => (this.activities = activities)
          });
        }
      }
    });
  }

  deleteObjective(objective: ObjetivoDto) {
    if (!objective.idObjesp) return;
    if (!confirm('¿Eliminar este objetivo?')) return;
    this.objetivosService.delete(objective.idObjesp).subscribe({
      next: () => {
        this.onProjectChange();
        this.objectiveForm = this.emptyObjective();
        this.selectedObjectiveId = null;
      }
    });
  }

  editActivity(activity: ActividadDto) {
    this.selectedActivityId = activity.idActi ?? null;
    this.activityForm = { ...activity };
  }

  saveActivity() {
    if (!this.selectedObjectiveId) {
      alert('Selecciona un objetivo específico');
      return;
    }
    const payload: ActividadDto = {
      ...this.activityForm,
      idObjesp: this.selectedObjectiveId,
      resultadoEsperadoActi: this.activityForm.resultadoEsperadoActi !== null ? Number(this.activityForm.resultadoEsperadoActi) : null,
      resultadoObtenido: this.activityForm.resultadoObtenido !== null ? Number(this.activityForm.resultadoObtenido) : null
    };
    if (!payload.nombreActi) {
      alert('Define el nombre de la actividad');
      return;
    }
    const request = this.selectedActivityId
      ? this.activitiesService.update(this.selectedActivityId, payload)
      : this.activitiesService.create(payload);
    request.subscribe({
      next: () => {
        this.onObjectiveChange();
        this.activityForm = this.emptyActivity();
        this.selectedActivityId = null;
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo guardar la actividad');
      }
    });
  }

  deleteActivity(activity: ActividadDto) {
    if (!activity.idActi) return;
    if (!confirm('¿Eliminar esta actividad?')) return;
    this.activitiesService.delete(activity.idActi).subscribe({
      next: () => this.onObjectiveChange()
    });
  }

  resetObjectiveForm() {
    this.objectiveForm = this.emptyObjective();
    this.selectedObjectiveId = null;
    this.activities = [];
  }

  resetActivityForm() {
    this.activityForm = this.emptyActivity();
    this.selectedActivityId = null;
  }

  private emptyObjective(): ObjetivoDto {
    return {
      objetivoObjesp: '',
      ejesObjesp: ''
    };
  }

  private emptyActivity(): ActividadDto {
    return {
      nombreActi: '',
      descripcionActi: '',
      resultadoEsperadoActi: null,
      resultadoObtenido: null,
      categoria: ''
    };
  }
}
