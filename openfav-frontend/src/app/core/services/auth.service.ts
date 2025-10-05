import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { tap } from 'rxjs/operators';

import { API_URL } from './api.config';
import { SessionService, SessionUser } from './session.service';

interface LoginResponse {
  id: number;
  email: string;
  role: 'ADMIN' | 'ONG';
}

@Injectable({ providedIn: 'root' })
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly session = inject(SessionService);

  login(email: string, password: string) {
    return this.http
      .post<LoginResponse>(`${API_URL}/auth/login`, { email, password })
      .pipe(tap((user) => this.session.setUser(user as SessionUser)));
  }

  logout() {
    this.session.setUser(null);
  }
}
