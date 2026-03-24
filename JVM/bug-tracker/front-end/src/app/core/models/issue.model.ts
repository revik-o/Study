export interface Comment {
  id: string;
  authorName: string;
  authorAvatar: string;
  date: Date;
  body: string;
}

export interface IssueDetail {
  key: string;
  title: string;
  description: string;
  status: string;
  assignee: { name: string; avatar: string } | null;
  reporter: { name: string; avatar: string };
  comments: Comment[];
}

export interface Issue {
  key: string;
  title: string;
  assigneeAvatar: string;
}

export interface IssueColumn {
  id: string;
  title: string;
  issues: Issue[];
}
