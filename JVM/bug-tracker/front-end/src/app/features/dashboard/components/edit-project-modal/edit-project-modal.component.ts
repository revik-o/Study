import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { Project } from '../../../../core/models/project.model';

@Component({
  selector: 'app-edit-project-modal',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  templateUrl: './edit-project-modal.component.html',
  styleUrls: ['./edit-project-modal.component.css']
})
export class EditProjectModalComponent {
  public projectForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<EditProjectModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Project
  ) {
    this.projectForm = this.fb.group({
      key: [data.key, Validators.required],
      name: [data.key + ' Name', Validators.required] // Mock name
    });
  }

  public onSave(): void {
    if (this.projectForm.valid) {
      // API call to update Project
      this.dialogRef.close({ ...this.data, ...this.projectForm.value });
    } else {
      this.projectForm.markAllAsTouched();
    }
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
