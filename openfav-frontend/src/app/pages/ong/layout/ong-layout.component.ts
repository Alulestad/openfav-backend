import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

import { AuthService } from '../../../core/services/auth.service';
import { SessionService } from '../../../core/services/session.service';

@Component({
  selector: 'app-ong-layout',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive],
  templateUrl: './ong-layout.component.html',
  styleUrl: './ong-layout.component.scss'
})
export class OngLayoutComponent {
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);
  protected readonly session = inject(SessionService);

  logout() {
    this.auth.logout();
    this.router.navigate(['/']);
  }
}
