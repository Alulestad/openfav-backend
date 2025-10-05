import { Routes } from '@angular/router';

import { authGuard, roleGuard } from './core/guards/auth.guard';
import { AdminBudgetsComponent } from './pages/admin/budgets/admin-budgets.component';
import { AdminDashboardComponent } from './pages/admin/dashboard/admin-dashboard.component';
import { AdminLayoutComponent } from './pages/admin/layout/admin-layout.component';
import { AdminProjectsComponent } from './pages/admin/projects/admin-projects.component';
import { LoginComponent } from './pages/login/login.component';
import { OngActivitiesComponent } from './pages/ong/activities/ong-activities.component';
import { OngLayoutComponent } from './pages/ong/layout/ong-layout.component';

export const routes: Routes = [
  { path: '', component: LoginComponent },
  {
    path: 'admin',
    component: AdminLayoutComponent,
    canActivate: [authGuard, roleGuard('ADMIN')],
    children: [
      { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
      { path: 'dashboard', component: AdminDashboardComponent },
      { path: 'projects', component: AdminProjectsComponent },
      { path: 'presupuestos', component: AdminBudgetsComponent }
    ]
  },
  {
    path: 'ong',
    component: OngLayoutComponent,
    canActivate: [authGuard, roleGuard('ONG')],
    children: [
      { path: '', redirectTo: 'actividades', pathMatch: 'full' },
      { path: 'actividades', component: OngActivitiesComponent }
    ]
  },
  { path: '**', redirectTo: '' }
];
