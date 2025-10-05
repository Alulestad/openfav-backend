import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { API_URL } from './api.config';

export interface Ong {
  idOng?: number;
  nombreOng: string;
  representanteLegalOng: string;
  emailOng: string;
  celularOng: string;
  direccionOng: string;
}

@Injectable({ providedIn: 'root' })
export class OngsService {
  private readonly http = inject(HttpClient);

  list(): Observable<Ong[]> {
    return this.http.get<Ong[]>(`${API_URL}/ongs`);
  }
}
