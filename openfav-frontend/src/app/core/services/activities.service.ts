import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { API_URL } from './api.config';

export interface ActividadDto {
  idActi?: number;
  idObjesp?: number;
  nombreActi: string;
  descripcionActi: string;
  resultadoEsperadoActi: number | null;
  resultadoObtenido: number | null;
  categoria: string;
}

@Injectable({ providedIn: 'root' })
export class ActivitiesService {
  private readonly http = inject(HttpClient);

  list(idObjesp?: number) {
    const params = idObjesp ? { params: { idObjesp } } : {};
    return this.http.get<ActividadDto[]>(`${API_URL}/actividades`, params);
  }

  create(payload: ActividadDto) {
    return this.http.post<ActividadDto>(`${API_URL}/actividades`, payload);
  }

  update(id: number, payload: ActividadDto) {
    return this.http.put<ActividadDto>(`${API_URL}/actividades/${id}`, payload);
  }

  delete(id: number) {
    return this.http.delete<void>(`${API_URL}/actividades/${id}`);
  }
}
