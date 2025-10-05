import { Injectable, signal } from '@angular/core';

type UserRole = 'ADMIN' | 'ONG';

export interface SessionUser {
  id: number;
  email: string;
  role: UserRole;
}

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly storageKey = 'openfav-user';
  private readonly userSignal = signal<SessionUser | null>(this.restoreUser());

  readonly user = this.userSignal.asReadonly();

  setUser(user: SessionUser | null) {
    this.userSignal.set(user);
    if (user) {
      localStorage.setItem(this.storageKey, JSON.stringify(user));
    } else {
      localStorage.removeItem(this.storageKey);
    }
  }

  isLoggedIn(): boolean {
    return this.userSignal() !== null;
  }

  hasRole(role: UserRole): boolean {
    return this.userSignal()?.role === role;
  }

  private restoreUser(): SessionUser | null {
    try {
      const raw = localStorage.getItem(this.storageKey);
      return raw ? (JSON.parse(raw) as SessionUser) : null;
    } catch (error) {
      console.warn('No se pudo restaurar la sesión', error);
      return null;
    }
  }
}
