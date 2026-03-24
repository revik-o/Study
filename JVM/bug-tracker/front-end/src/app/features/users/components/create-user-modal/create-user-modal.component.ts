import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-create-user-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './create-user-modal.component.html',
  styleUrls: ['./create-user-modal.component.css']
})
export class CreateUserModalComponent {
  public userForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CreateUserModalComponent>
  ) {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      avatarUrl: ['https://i.pravatar.cc/150'] // Default mock avatar
    });
  }

  public onSave(): void {
    if (this.userForm.valid) {
      // API call to save User
      this.dialogRef.close(this.userForm.value);
    } else {
      this.userForm.markAllAsTouched();
    }
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
