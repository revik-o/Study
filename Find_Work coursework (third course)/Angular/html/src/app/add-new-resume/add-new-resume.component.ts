import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-new-resume',
  templateUrl: './add-new-resume.component.html',
  styleUrls: ['./add-new-resume.component.css']
})
export class AddNewResumeComponent implements OnInit {

  email = 'oleg@gmail.com';

  constructor() { }

  ngOnInit(): void {
  }

}
