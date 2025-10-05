import { CommonModule } from '@angular/common';
import { Component, inject, AfterViewInit, OnDestroy, ViewChild, ElementRef } from '@angular/core';
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

  @ViewChild('growthCanvas', { static: false }) private growthCanvas?: ElementRef<HTMLCanvasElement>;
  private chart: any | null = null;

  ngAfterViewInit(): void {
    // Chart.js is dynamically imported so the app doesn't break if the package isn't installed yet.
    // The chart uses burned (hardcoded) data with ups and downs to simulate growth/decline.
    import('chart.js/auto')
      .then(({ default: Chart }) => {
        const canvasEl = this.growthCanvas?.nativeElement;
        const ctx = canvasEl?.getContext('2d');
        if (!ctx) {
          // reveal fallback svg if canvas is not available
          const svg = canvasEl?.parentElement?.querySelector('.chart-fallback') as HTMLElement | null;
          if (svg) svg.style.display = 'block';
          return;
        }

        const labels = ['Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic'];
        const projectsData = [12, 15, 14, 18, 22, 20, 24, 27, 25, 30, 28, 32];
        const budgetData = [5, 6, 6.5, 7, 9, 8.5, 10, 11, 10.5, 12, 11.8, 13];
        const activitiesData = [20, 18, 22, 25, 23, 28, 26, 30, 29, 31, 27, 33];

        this.chart = new Chart(ctx, {
          type: 'line',
          data: {
            labels,
            datasets: [
              {
                label: 'Proyectos',
                data: projectsData,
                borderColor: 'rgba(54, 162, 235, 1)',
                backgroundColor: 'rgba(54, 162, 235, 0.15)',
                tension: 0.3,
                fill: true
              },
              {
                label: 'Presupuesto (k USD)',
                data: budgetData,
                borderColor: 'rgba(75, 192, 192, 1)',
                backgroundColor: 'rgba(75, 192, 192, 0.12)',
                tension: 0.3,
                fill: true
              },
              {
                label: 'Actividades',
                data: activitiesData,
                borderColor: 'rgba(255, 159, 64, 1)',
                backgroundColor: 'rgba(255, 159, 64, 0.12)',
                tension: 0.3,
                fill: true
              }
            ]
          },
          options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
              legend: { position: 'top' }
            },
            scales: {
              y: { beginAtZero: false }
            }
          }
        });
      })
      .catch((err) => {
        console.warn('No se pudo cargar Chart.js', err);
        const canvasEl = this.growthCanvas?.nativeElement;
        const svg = canvasEl?.parentElement?.querySelector('.chart-fallback') as HTMLElement | null;
        if (svg) svg.style.display = 'block';
      });
  }

  ngOnDestroy(): void {
    if (this.chart && typeof this.chart.destroy === 'function') {
      this.chart.destroy();
      this.chart = null;
    }
  }

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
