import { Component, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { EditProjectModalComponent } from '../edit-project-modal/edit-project-modal.component';
import { DeleteProjectModalComponent } from '../delete-project-modal/delete-project-modal.component';
import { Project } from '../../../../core/models/project.model';

const PROJECT_DATA: Project[] = [
  { id: '1', icon: 'business_center', key: 'FRONT', issueCount: 12 },
  { id: '2', icon: 'dns', key: 'BACK', issueCount: 5 },
  { id: '3', icon: 'bug_report', key: 'QA', issueCount: 0 }
];

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatButtonModule, MatIconModule, MatDialogModule],
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent {
  @Output() projectSelected = new EventEmitter<string>();

  public displayedColumns: string[] = ['icon', 'key', 'issueCount', 'actions'];
  public dataSource = PROJECT_DATA;

  constructor(private dialog: MatDialog) { }

  public onRowClick(project: Project): void {
    this.projectSelected.emit(project.key);
  }

  public openEditProjectModal(event: Event, project: Project): void {
    event.stopPropagation();
    const dialogRef = this.dialog.open(EditProjectModalComponent, {
      width: '400px',
      data: project
    });
    dialogRef.afterClosed().subscribe();
  }

  public openDeleteProjectModal(event: Event, project: Project): void {
    event.stopPropagation();
    const dialogRef = this.dialog.open(DeleteProjectModalComponent, {
      width: '400px',
      data: project
    });
    dialogRef.afterClosed().subscribe();
  }
}
