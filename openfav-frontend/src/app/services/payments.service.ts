import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Observable } from 'rxjs';

export interface ValidateRequest {
  date: string;
  amount: string;
  token: string;
}

export interface ValidateResponse {
  status: 'succeeded' | 'failed' | string;
  // puedes agregar más campos si tu API los devuelve
}

@Injectable({ providedIn: 'root' })
export class PaymentsService {
  private baseUrl = environment.apiBaseUrl; // e.g. 'http://localhost:8080'

  constructor(private http: HttpClient) {}

  validateTransaction(body: ValidateRequest): Observable<ValidateResponse> {
    // Tu backend expone un endpoint propio que a su vez llama a PagoPlux
    return this.http.post<ValidateResponse>(`${this.baseUrl}/payments/validate`, body);
  }
}
