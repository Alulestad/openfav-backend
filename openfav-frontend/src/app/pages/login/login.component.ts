import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../../core/services/auth.service';
import { SessionService } from '../../core/services/session.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  private readonly auth = inject(AuthService);
  private readonly router = inject(Router);
  private readonly session = inject(SessionService);

  errorMessage = '';
  loading = false;
  model = {
    email: 'admin',
    password: 'admin'
  };

  ngOnInit() {
    const user = this.session.user();
    if (user) {
      this.redirectByRole(user.role);
    }
  }

  submit() {
    if (!this.model.email || !this.model.password) {
      this.errorMessage = 'Ingresa el usuario y la contraseña';
      return;
    }
    this.loading = true;
    this.errorMessage = '';
    this.auth.login(this.model.email, this.model.password).subscribe({
      next: (user) => {
        this.loading = false;
        this.redirectByRole(user.role);
      },
      error: (err) => {
        console.error(err);
        this.loading = false;
        this.errorMessage = err?.error?.message ?? 'Credenciales incorrectas';
      }
    });
  }

  private redirectByRole(role: 'ADMIN' | 'ONG') {
    if (role === 'ADMIN') {
      this.router.navigate(['/admin']);
    } else {
      this.router.navigate(['/ong']);
    }
  }
}
