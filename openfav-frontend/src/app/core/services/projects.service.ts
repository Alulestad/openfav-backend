import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { API_URL } from './api.config';

export interface ProyectoDto {
  idProy?: number;
  idOng?: number;
  tituloProy: string;
  objetivoGeneralProy: string;
  alcanceProy: number;
  montoTotalProy: string;
  plazoTotalProy: number;
  fechaInicioProy?: string;
  fechaFinProy?: string;
  ejesProy: string;
  resultadoProy: string;
}

@Injectable({ providedIn: 'root' })
export class ProjectsService {
  private readonly http = inject(HttpClient);

  list(idOng?: number): Observable<ProyectoDto[]> {
    const params = idOng ? { params: { idOng } } : {};
    return this.http.get<ProyectoDto[]>(`${API_URL}/proyectos`, params);
  }

  get(id: number) {
    return this.http.get<ProyectoDto>(`${API_URL}/proyectos/${id}`);
  }

  create(payload: ProyectoDto) {
    return this.http.post<ProyectoDto>(`${API_URL}/proyectos`, payload);
  }

  update(id: number, payload: ProyectoDto) {
    return this.http.put<ProyectoDto>(`${API_URL}/proyectos/${id}`, payload);
  }

  delete(id: number) {
    return this.http.delete<void>(`${API_URL}/proyectos/${id}`);
  }
}
