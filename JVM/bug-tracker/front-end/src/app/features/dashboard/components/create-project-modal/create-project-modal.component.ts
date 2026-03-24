import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-create-project-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './create-project-modal.component.html',
  styleUrls: ['./create-project-modal.component.css']
})
export class CreateProjectModalComponent {
  public projectForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CreateProjectModalComponent>
  ) {
    this.projectForm = this.fb.group({
      key: ['', Validators.required],
      name: ['', Validators.required],
      icon: ['business_center'] // Default mock icon
    });
  }

  public onSave(): void {
    if (this.projectForm.valid) {
      // API call to save Project
      this.dialogRef.close(this.projectForm.value);
    } else {
      this.projectForm.markAllAsTouched();
    }
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
