import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatToolbarModule } from '@angular/material/toolbar';
import { CreateUserModalComponent } from '../../components/create-user-modal/create-user-modal.component';
import { EditUserModalComponent } from '../../components/edit-user-modal/edit-user-modal.component';
import { DeleteUserModalComponent } from '../../components/delete-user-modal/delete-user-modal.component';
import { User } from '../../../../core/models/user.model';

const ELEMENT_DATA: User[] = [
  { id: '1', avatarUrl: 'https://i.pravatar.cc/150?u=1', firstName: 'John', lastName: 'Doe', email: 'john@example.com' },
  { id: '2', avatarUrl: 'https://i.pravatar.cc/150?u=2', firstName: 'Jane', lastName: 'Smith', email: 'jane@example.com' }
];

@Component({
  selector: 'app-user-management-page',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatDialogModule, MatToolbarModule],
  templateUrl: './user-management-page.component.html',
  styleUrls: ['./user-management-page.component.css']
})
export class UserManagementPageComponent implements OnInit {
  public displayedColumns: string[] = ['avatar', 'name', 'email', 'actions'];
  public dataSource = ELEMENT_DATA;

  constructor(private dialog: MatDialog) { }

  ngOnInit(): void {
    // API call replacement
  }

  public openCreateUserModal(): void {
    const dialogRef = this.dialog.open(CreateUserModalComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Handle result
      }
    });
  }

  public openEditUserModal(user: User): void {
    const dialogRef = this.dialog.open(EditUserModalComponent, {
      width: '400px',
      data: user
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Handle result
      }
    });
  }

  public openDeleteUserModal(user: User): void {
    const dialogRef = this.dialog.open(DeleteUserModalComponent, {
      width: '400px',
      data: user
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        // Handle result
      }
    });
  }
}
