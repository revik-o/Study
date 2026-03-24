import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatDividerModule } from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { IssueDetail } from '../../../../core/models/issue.model';
@Component({
  selector: 'app-issue-page',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatChipsModule, MatDividerModule, MatIconModule, MatButtonModule],
  templateUrl: './issue-page.component.html',
  styleUrls: ['./issue-page.component.css']
})
export class IssuePageComponent implements OnInit {
  @Input() issueKey!: string;

  public issueDetail!: IssueDetail;

  ngOnInit(): void {
    // Mock fetch issue details from API
    this.issueDetail = {
      key: this.issueKey,
      title: 'Setup project with Feature-Driven Development architecture',
      description: 'We need to create the base angular application with core module and feature modules separated. Also install Material Design.',
      status: 'In Progress',
      assignee: { name: 'John Doe', avatar: 'https://i.pravatar.cc/150?u=1' },
      reporter: { name: 'Jane Smith', avatar: 'https://i.pravatar.cc/150?u=2' },
      comments: [
        {
          id: 'c1',
          authorName: 'John Doe',
          authorAvatar: 'https://i.pravatar.cc/150?u=1',
          date: new Date('2023-10-25T10:00:00Z'),
          body: 'I have started working on this.'
        }
      ]
    };
  }
}
