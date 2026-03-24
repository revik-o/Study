import { Component, Input, EventEmitter, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CdkDragDrop, DragDropModule, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { Issue, IssueColumn } from '../../../../core/models/issue.model';
@Component({
  selector: 'app-issue-dashboard',
  standalone: true,
  imports: [CommonModule, DragDropModule, MatCardModule, MatIconModule],
  templateUrl: './issue-dashboard.component.html',
  styleUrls: ['./issue-dashboard.component.css']
})
export class IssueDashboardComponent {
  @Input() projectKey!: string;
  @Output() issueSelected = new EventEmitter<string>();

  public columns: IssueColumn[] = [
    {
      id: 'todo',
      title: 'To Do',
      issues: [
        { key: 'FRONT-1', title: 'Setup project', assigneeAvatar: 'https://i.pravatar.cc/150?u=1' },
        { key: 'FRONT-2', title: 'Login page styling', assigneeAvatar: 'https://i.pravatar.cc/150?u=2' }
      ]
    },
    {
      id: 'in-progress',
      title: 'In Progress',
      issues: [
        { key: 'FRONT-3', title: 'Build Dashboard UI', assigneeAvatar: 'https://i.pravatar.cc/150?u=1' }
      ]
    },
    {
      id: 'done',
      title: 'Done',
      issues: [
        { key: 'FRONT-4', title: 'Initialize repo', assigneeAvatar: 'https://i.pravatar.cc/150?u=3' }
      ]
    }
  ];

  public get connectedLists(): string[] {
    return this.columns.map(c => c.id);
  }

  public drop(event: CdkDragDrop<Issue[]>): void {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  public onIssueClick(issue: Issue): void {
    this.issueSelected.emit(issue.key);
  }
}
