import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

import { SessionService } from '../services/session.service';

export const authGuard: CanActivateFn = () => {
  const session = inject(SessionService);
  const router = inject(Router);
  if (!session.isLoggedIn()) {
    router.navigate(['/']);
    return false;
  }
  return true;
};

export const roleGuard = (role: 'ADMIN' | 'ONG'): CanActivateFn => () => {
  const session = inject(SessionService);
  const router = inject(Router);
  if (!session.isLoggedIn() || !session.hasRole(role)) {
    router.navigate(['/']);
    return false;
  }
  return true;
};
