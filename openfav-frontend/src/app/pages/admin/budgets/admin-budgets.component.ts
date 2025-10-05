import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { BudgetsService, PresupuestoDto } from '../../../core/services/budgets.service';
import { ProjectsService, ProyectoDto } from '../../../core/services/projects.service';
import { SolicitudesService, SolicitudDto } from '../../../core/services/solicitudes.service';

@Component({
  selector: 'app-admin-budgets',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-budgets.component.html',
  styleUrl: './admin-budgets.component.scss'
})
export class AdminBudgetsComponent {
  private readonly projectsService = inject(ProjectsService);
  private readonly budgetsService = inject(BudgetsService);
  private readonly solicitudesService = inject(SolicitudesService);

  projects: ProyectoDto[] = [];
  budgets: PresupuestoDto[] = [];
  solicitudes: SolicitudDto[] = [];

  selectedProjectId: number | null = null;
  selectedBudgetId: number | null = null;

  budgetForm: PresupuestoDto = this.emptyBudget();
  solicitudForm: SolicitudDto = this.emptySolicitud();

  ngOnInit() {
    this.projectsService.list().subscribe({ next: (projects) => (this.projects = projects) });
  }

  onProjectChange() {
    if (!this.selectedProjectId) {
      this.budgets = [];
      this.solicitudes = [];
      return;
    }
    this.budgetsService.list(this.selectedProjectId).subscribe({
      next: (budgets) => (this.budgets = budgets)
    });
    this.solicitudesService.list({ idProy: this.selectedProjectId }).subscribe({
      next: (solicitudes) => (this.solicitudes = solicitudes)
    });
  }

  editBudget(budget: PresupuestoDto) {
    this.selectedBudgetId = budget.idPres ?? null;
    this.budgetForm = { ...budget };
  }

  saveBudget() {
    if (!this.selectedProjectId) {
      alert('Selecciona un proyecto');
      return;
    }
    const payload: PresupuestoDto = {
      ...this.budgetForm,
      idProy: this.selectedProjectId,
      cantidadPres: this.budgetForm.cantidadPres !== null ? Number(this.budgetForm.cantidadPres) : null,
      unidadesPres: this.budgetForm.unidadesPres !== null ? Number(this.budgetForm.unidadesPres) : null,
      valorPres: this.budgetForm.valorPres !== null ? Number(this.budgetForm.valorPres) : null
    };
    if (!payload.categoriaPres) {
      alert('Ingresa la categoría del presupuesto');
      return;
    }
    const request = this.selectedBudgetId
      ? this.budgetsService.update(this.selectedBudgetId, payload)
      : this.budgetsService.create(payload);
    request.subscribe({
      next: () => {
        this.onProjectChange();
        this.clearBudgetSelection();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo guardar el presupuesto');
      }
    });
  }

  deleteBudget(budget: PresupuestoDto) {
    if (!budget.idPres) return;
    if (!confirm('¿Eliminar este presupuesto?')) return;
    this.budgetsService.delete(budget.idPres).subscribe({
      next: () => this.onProjectChange()
    });
  }

  clearBudgetSelection() {
    this.selectedBudgetId = null;
    this.budgetForm = this.emptyBudget();
  }

  prepareSolicitud(budget: PresupuestoDto) {
    if (!budget.idPres) return;
    this.solicitudForm = {
      idPres: budget.idPres,
      documentoDescm: '',
      estadoDescm: 'Enviado'
    };
  }

  handleFileSelection(event: Event) {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];
    if (file) {
      this.solicitudForm.documentoDescm = file.name;
    }
  }

  saveSolicitud() {
    if (!this.solicitudForm.idPres) {
      alert('Selecciona un presupuesto para vincular la solicitud');
      return;
    }
    if (!this.solicitudForm.documentoDescm) {
      alert('Selecciona un archivo para registrar el nombre');
      return;
    }
    const request = this.solicitudForm.idDescm
      ? this.solicitudesService.update(this.solicitudForm.idDescm, this.solicitudForm)
      : this.solicitudesService.create(this.solicitudForm);
    request.subscribe({
      next: () => {
        this.onProjectChange();
        this.solicitudForm = this.emptySolicitud();
      }
    });
  }

  updateSolicitudStatus(solicitud: SolicitudDto, estado: string) {
    this.solicitudesService
      .update(solicitud.idDescm!, { ...solicitud, estadoDescm: estado })
      .subscribe({ next: () => this.onProjectChange() });
  }

  getBudgetLabel(idPres?: number) {
    if (!idPres) {
      return '';
    }
    return this.budgets.find((b) => b.idPres === idPres)?.categoriaPres ?? '';
  }

  private emptyBudget(): PresupuestoDto {
    return {
      categoriaPres: '',
      cantidadPres: null,
      unidadesPres: null,
      valorPres: null
    };
  }

  private emptySolicitud(): SolicitudDto {
    return {
      documentoDescm: '',
      estadoDescm: 'Enviado'
    };
  }
}
