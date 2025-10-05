import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { API_URL } from './api.config';

export interface PresupuestoDto {
  idPres?: number;
  idProy?: number;
  categoriaPres: string;
  cantidadPres: number | null;
  unidadesPres: number | null;
  valorPres: number | null;
}

@Injectable({ providedIn: 'root' })
export class BudgetsService {
  private readonly http = inject(HttpClient);

  list(idProy?: number) {
    const params = idProy ? { params: { idProy } } : {};
    return this.http.get<PresupuestoDto[]>(`${API_URL}/presupuestos`, params);
  }

  create(payload: PresupuestoDto) {
    return this.http.post<PresupuestoDto>(`${API_URL}/presupuestos`, payload);
  }

  update(id: number, payload: PresupuestoDto) {
    return this.http.put<PresupuestoDto>(`${API_URL}/presupuestos/${id}`, payload);
  }

  delete(id: number) {
    return this.http.delete<void>(`${API_URL}/presupuestos/${id}`);
  }
}
