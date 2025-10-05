import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { ObjetivoDto, ObjetivosService } from '../../../core/services/objetivos.service';
import { Ong, OngsService } from '../../../core/services/ongs.service';
import { ProyectoDto, ProjectsService } from '../../../core/services/projects.service';

@Component({
  selector: 'app-admin-projects',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-projects.component.html',
  styleUrl: './admin-projects.component.scss'
})
export class AdminProjectsComponent {
  private readonly projectsService = inject(ProjectsService);
  private readonly ongsService = inject(OngsService);
  private readonly objetivosService = inject(ObjetivosService);

  projects: ProyectoDto[] = [];
  ongs: Ong[] = [];
  objectives: ObjetivoDto[] = [];

  selectedProjectId: number | null = null;
  projectForm: ProyectoDto = this.emptyProject();

  objectiveForm: ObjetivoDto = this.emptyObjective();
  selectedObjectiveId: number | null = null;

  ngOnInit() {
    this.loadProjects();
    this.ongsService.list().subscribe({ next: (ongs) => (this.ongs = ongs) });
  }

  loadProjects() {
    this.projectsService.list().subscribe({
      next: (projects) => {
        this.projects = projects;
        if (this.selectedProjectId) {
          const match = projects.find((p) => p.idProy === this.selectedProjectId);
          if (!match) {
            this.clearProjectSelection();
          }
        }
      },
      error: (err) => console.error('Error cargando proyectos', err)
    });
  }

  editProject(project: ProyectoDto) {
    this.selectedProjectId = project.idProy ?? null;
    this.projectForm = {
      idProy: project.idProy,
      idOng: project.idOng,
      tituloProy: project.tituloProy,
      objetivoGeneralProy: project.objetivoGeneralProy,
      alcanceProy: project.alcanceProy,
      montoTotalProy: project.montoTotalProy,
      plazoTotalProy: project.plazoTotalProy,
      fechaInicioProy: project.fechaInicioProy ?? '',
      fechaFinProy: project.fechaFinProy ?? '',
      ejesProy: project.ejesProy,
      resultadoProy: project.resultadoProy
    };
    if (project.idProy) {
      this.objetivosService.list(project.idProy).subscribe({
        next: (objectives) => (this.objectives = objectives)
      });
    }
  }

  saveProject() {
    const payload: ProyectoDto = {
      ...this.projectForm,
      idOng: this.projectForm.idOng ? Number(this.projectForm.idOng) : undefined,
      alcanceProy: Number(this.projectForm.alcanceProy),
      plazoTotalProy: Number(this.projectForm.plazoTotalProy)
    };
    if (!payload.tituloProy) {
      alert('Completa al menos el título del proyecto');
      return;
    }
    const request = this.selectedProjectId
      ? this.projectsService.update(this.selectedProjectId, payload)
      : this.projectsService.create(payload);

    request.subscribe({
      next: () => {
        this.loadProjects();
        alert('Proyecto guardado correctamente');
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo guardar el proyecto');
      }
    });
  }

  deleteProject(project: ProyectoDto) {
    if (!project.idProy) return;
    if (!confirm('¿Eliminar este proyecto?')) return;
    this.projectsService.delete(project.idProy).subscribe({
      next: () => {
        this.loadProjects();
        this.clearProjectSelection();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo eliminar el proyecto');
      }
    });
  }

  clearProjectSelection() {
    this.selectedProjectId = null;
    this.projectForm = this.emptyProject();
    this.objectives = [];
    this.clearObjectiveSelection();
  }

  editObjective(objective: ObjetivoDto) {
    this.selectedObjectiveId = objective.idObjesp ?? null;
    this.objectiveForm = { ...objective };
  }

  saveObjective() {
    if (!this.selectedProjectId) {
      alert('Selecciona un proyecto primero');
      return;
    }
    const payload: ObjetivoDto = {
      ...this.objectiveForm,
      idProy: this.selectedProjectId
    };
    if (!payload.objetivoObjesp) {
      alert('Describe el objetivo específico');
      return;
    }
    const request = this.selectedObjectiveId
      ? this.objetivosService.update(this.selectedObjectiveId, payload)
      : this.objetivosService.create(payload);
    request.subscribe({
      next: () => {
        this.loadObjectives(this.selectedProjectId!);
        this.clearObjectiveSelection();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo guardar el objetivo');
      }
    });
  }

  deleteObjective(objective: ObjetivoDto) {
    if (!objective.idObjesp) return;
    if (!confirm('¿Eliminar este objetivo específico?')) return;
    this.objetivosService.delete(objective.idObjesp).subscribe({
      next: () => {
        if (this.selectedProjectId) {
          this.loadObjectives(this.selectedProjectId);
        }
      }
    });
  }

  private loadObjectives(projectId: number) {
    this.objetivosService.list(projectId).subscribe({
      next: (objectives) => (this.objectives = objectives)
    });
  }

  clearObjectiveSelection() {
    this.selectedObjectiveId = null;
    this.objectiveForm = this.emptyObjective();
  }

  private emptyProject(): ProyectoDto {
    return {
      tituloProy: '',
      objetivoGeneralProy: '',
      alcanceProy: 0,
      montoTotalProy: '',
      plazoTotalProy: 0,
      fechaInicioProy: '',
      fechaFinProy: '',
      ejesProy: '',
      resultadoProy: ''
    };
  }

  private emptyObjective(): ObjetivoDto {
    return {
      objetivoObjesp: '',
      ejesObjesp: ''
    };
  }
}
