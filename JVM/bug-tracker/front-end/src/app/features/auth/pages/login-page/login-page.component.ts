import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { LoginRequest } from '../../../../core/models/auth.model';

interface LoginForm {
  email: FormControl<string>;
  password: FormControl<string>;
}
@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule
  ],
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  public loginForm: FormGroup<LoginForm>;
  public isLoading: boolean = false;

  constructor(private readonly router: Router) {
    this.loginForm = new FormGroup<LoginForm>({
      email: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.email] }),
      password: new FormControl('', { nonNullable: true, validators: [Validators.required] })
    });
  }

  public onLogin(): void {
    if (this.loginForm.valid) {
      this.isLoading = true;
      const request: LoginRequest = this.loginForm.getRawValue();
      
      setTimeout(() => {
        this.isLoading = false;
        this.router.navigate(['/app']);
      }, 1000);
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
