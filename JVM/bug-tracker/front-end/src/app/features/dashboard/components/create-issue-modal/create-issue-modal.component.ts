import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-create-issue-modal',
  standalone: true,
  imports: [
    CommonModule, 
    ReactiveFormsModule, 
    MatDialogModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './create-issue-modal.component.html',
  styleUrls: ['./create-issue-modal.component.css']
})
export class CreateIssueModalComponent {
  public issueForm: FormGroup;

  public users = [
    { id: '1', name: 'John Doe' },
    { id: '2', name: 'Jane Smith' }
  ];

  public statuses = ['To Do', 'In Progress', 'Done'];

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CreateIssueModalComponent>
  ) {
    this.issueForm = this.fb.group({
      title: ['', Validators.required],
      description: [''],
      status: ['To Do', Validators.required],
      assigneeId: [null]
    });
  }

  public onSave(): void {
    if (this.issueForm.valid) {
      // API call to create issue
      this.dialogRef.close(this.issueForm.value);
    } else {
      this.issueForm.markAllAsTouched();
    }
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
