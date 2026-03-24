import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormGroup, Validators, AbstractControlOptions, FormControl, AbstractControl, ValidationErrors } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { UserApiService } from '../../../../core/services/user.api.service';
import { RegisterRequest } from '../../../../core/models/auth.model';

interface RegisterForm {
  email: FormControl<string>;
  firstName: FormControl<string>;
  lastName: FormControl<string>;
  password: FormControl<string>;
  passwordVerification: FormControl<string>;
}
@Component({
  selector: 'app-registration-page',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterLink,
    MatCardModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatFormFieldModule,
  ],
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {
  public registerForm: FormGroup<RegisterForm>;
  public isLoading: boolean = false;

  constructor(private readonly router: Router, private readonly userApiService: UserApiService) {
    this.registerForm = new FormGroup<RegisterForm>({
      email: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.email] }),
      firstName: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      lastName: new FormControl('', { nonNullable: true, validators: [Validators.required] }),
      password: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.minLength(6)] }),
      passwordVerification: new FormControl('', { nonNullable: true, validators: [Validators.required] })
    }, { validators: (abstractControl: AbstractControl) => this.passwordMatchValidator(abstractControl) });
  }

  private passwordMatchValidator(abstractControl: AbstractControl): ValidationErrors | null {
    return abstractControl.get('password')?.value === abstractControl.get('passwordVerification')?.value
      ? null : { mismatch: true };
  }

  public onRegister(): void {
    if (this.registerForm.valid) {
      this.isLoading = true;
      const request: RegisterRequest = this.registerForm.getRawValue();
      // this.apiService.
      // setTimeout(() => {
      //   this.isLoading = false;
      //   this.router.navigate(['/login']);
      // }, 1000);
    } else {
      this.registerForm.markAllAsTouched();
    }
  }
}
