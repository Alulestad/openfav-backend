import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { API_URL } from './api.config';

export interface ObjetivoDto {
  idObjesp?: number;
  idProy?: number;
  objetivoObjesp: string;
  ejesObjesp: string;
}

@Injectable({ providedIn: 'root' })
export class ObjetivosService {
  private readonly http = inject(HttpClient);

  list(idProy?: number) {
    const params = idProy ? { params: { idProy } } : {};
    return this.http.get<ObjetivoDto[]>(`${API_URL}/objetivos`, params);
  }

  create(payload: ObjetivoDto) {
    return this.http.post<ObjetivoDto>(`${API_URL}/objetivos`, payload);
  }

  update(id: number, payload: ObjetivoDto) {
    return this.http.put<ObjetivoDto>(`${API_URL}/objetivos/${id}`, payload);
  }

  delete(id: number) {
    return this.http.delete<void>(`${API_URL}/objetivos/${id}`);
  }
}
