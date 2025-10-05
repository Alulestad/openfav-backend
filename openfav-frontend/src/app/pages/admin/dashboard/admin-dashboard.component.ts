import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { firstValueFrom } from 'rxjs';

import { ActivitiesService } from '../../../core/services/activities.service';
import { BudgetsService } from '../../../core/services/budgets.service';
import { ProjectsService } from '../../../core/services/projects.service';
import { SolicitudesService } from '../../../core/services/solicitudes.service';

@Component({
  selector: 'app-admin-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.scss'
})
export class AdminDashboardComponent {
  private readonly projectsService = inject(ProjectsService);
  private readonly budgetsService = inject(BudgetsService);
  private readonly activitiesService = inject(ActivitiesService);
  private readonly solicitudesService = inject(SolicitudesService);

  loading = false;
  metrics = {
    projects: 0,
    totalBudget: 0,
    activities: 0,
    solicitudes: {
      enviado: 0,
      revisando: 0,
      aprobado: 0
    }
  };

  ngOnInit() {
    this.loadMetrics();
  }

  private async loadMetrics() {
    this.loading = true;
    try {
      const [projects, budgets, activities, solicitudes] = await Promise.all([
        firstValueFrom(this.projectsService.list()),
        firstValueFrom(this.budgetsService.list()),
        firstValueFrom(this.activitiesService.list()),
        firstValueFrom(this.solicitudesService.list())
      ]);
      this.metrics.projects = projects?.length ?? 0;
      this.metrics.totalBudget = (budgets ?? []).reduce((sum, item) => sum + (Number(item.valorPres) || 0), 0);
      this.metrics.activities = activities?.length ?? 0;
      const normalized = (solicitudes ?? []).map((s) => s.estadoDescm?.toLowerCase() ?? '');
      this.metrics.solicitudes = {
        enviado: normalized.filter((s) => s === 'enviado').length,
        revisando: normalized.filter((s) => s === 'revisando').length,
        aprobado: normalized.filter((s) => s === 'aprobado').length
      };
    } catch (error) {
      console.error('No se pudieron cargar las métricas', error);
    } finally {
      this.loading = false;
    }
  }
}
