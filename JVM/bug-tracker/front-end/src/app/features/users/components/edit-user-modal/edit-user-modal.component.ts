import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { User } from '../../../../core/models/user.model';

@Component({
  selector: 'app-edit-user-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './edit-user-modal.component.html',
  styleUrls: ['./edit-user-modal.component.css']
})
export class EditUserModalComponent {
  public userForm: FormGroup;

  constructor(
    private readonly formBuilder: FormBuilder,
    public dialogRef: MatDialogRef<EditUserModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) {
    this.userForm = this.formBuilder.group({
      firstName: [data.firstName, Validators.required],
      lastName: [data.lastName, Validators.required],
      email: [data.email, [Validators.required, Validators.email]]
    });
  }

  public onSave(): void {
    if (this.userForm.valid) {
      // API call to update user data
      this.dialogRef.close({ ...this.data, ...this.userForm.value });
    } else {
      this.userForm.markAllAsTouched();
    }
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
