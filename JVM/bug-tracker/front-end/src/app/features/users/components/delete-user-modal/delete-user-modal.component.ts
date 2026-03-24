import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_DIALOG_DATA, MatDialogRef, MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { User } from '../../../../core/models/user.model';

@Component({
  selector: 'app-delete-user-modal',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatButtonModule],
  templateUrl: './delete-user-modal.component.html',
  styleUrls: ['./delete-user-modal.component.css']
})
export class DeleteUserModalComponent {
  constructor(
    public dialogRef: MatDialogRef<DeleteUserModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User
  ) { }

  public onDelete(): void {
    // API call to delete User
    this.dialogRef.close('delete');
  }

  public onCancel(): void {
    this.dialogRef.close();
  }
}
