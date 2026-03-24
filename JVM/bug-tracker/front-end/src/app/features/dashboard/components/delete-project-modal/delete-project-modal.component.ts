import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { Project } from '../../../../core/models/project.model';

@Component({
  selector: 'app-delete-project-modal',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatButtonModule],
  templateUrl: './delete-project-modal.component.html',
  styleUrls: ['./delete-project-modal.component.css']
})
export class DeleteProjectModalComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteProjectModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Project
  ) { }

  public onDelete(): void {
    // API call to delete Project
    this.dialogRef.close('delete');
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
