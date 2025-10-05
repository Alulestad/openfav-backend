import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { API_URL } from './api.config';

export interface SolicitudDto {
  idDescm?: number;
  idPres?: number;
  documentoDescm: string;
  estadoDescm: string;
}

@Injectable({ providedIn: 'root' })
export class SolicitudesService {
  private readonly http = inject(HttpClient);

  list(params?: { idPres?: number; idProy?: number }) {
    const options = params ? { params: { ...params } as any } : {};
    return this.http.get<SolicitudDto[]>(`${API_URL}/solicitudes`, options);
  }

  create(payload: SolicitudDto) {
    return this.http.post<SolicitudDto>(`${API_URL}/solicitudes`, payload);
  }

  update(id: number, payload: SolicitudDto) {
    return this.http.put<SolicitudDto>(`${API_URL}/solicitudes/${id}`, payload);
  }

  delete(id: number) {
    return this.http.delete<void>(`${API_URL}/solicitudes/${id}`);
  }
}
