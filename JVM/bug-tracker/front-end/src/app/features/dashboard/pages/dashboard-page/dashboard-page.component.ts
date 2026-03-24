import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { ProjectListComponent } from '../../components/project-list/project-list.component';
import { IssueDashboardComponent } from '../../components/issue-dashboard/issue-dashboard.component';
import { IssuePageComponent } from '../../components/issue-page/issue-page.component';
import { CreateProjectModalComponent } from '../../components/create-project-modal/create-project-modal.component';
import { CreateIssueModalComponent } from '../../components/create-issue-modal/create-issue-modal.component';
import { DashboardState } from '../../../../core/models/dashboard.model';
@Component({
  selector: 'app-dashboard-page',
  standalone: true,
  imports: [
    CommonModule, 
    MatToolbarModule, 
    MatButtonModule, 
    MatIconModule, 
    MatDialogModule,
    ProjectListComponent,
    IssueDashboardComponent,
    IssuePageComponent
  ],
  templateUrl: './dashboard-page.component.html',
  styleUrls: ['./dashboard-page.component.css']
})
export class DashboardPageComponent {
  public state: DashboardState = {
    projectKey: null,
    issueKey: null
  };

  constructor(private dialog: MatDialog) {}

  public get isPathEmpty(): boolean {
    return !this.state.projectKey && !this.state.issueKey;
  }

  public get isProjectSelected(): boolean {
    return !!this.state.projectKey && !this.state.issueKey;
  }

  public get isIssueSelected(): boolean {
    return !!this.state.projectKey && !!this.state.issueKey;
  }

  public navigateToProjects(): void {
    this.state = { projectKey: null, issueKey: null };
  }

  public navigateToProject(key: string): void {
    this.state = { projectKey: key, issueKey: null };
  }

  public navigateToIssue(key: string): void {
    this.state = { ...this.state, issueKey: key };
  }

  public openCreateProjectModal(): void {
    const dialogRef = this.dialog.open(CreateProjectModalComponent, { width: '400px' });
    dialogRef.afterClosed().subscribe(res => {
      // Handle project creation
    });
  }

  public openCreateIssueModal(): void {
    const dialogRef = this.dialog.open(CreateIssueModalComponent, { width: '600px' });
    dialogRef.afterClosed().subscribe(res => {
      // Handle issue creation
    });
  }
}
