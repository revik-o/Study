import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-find-people',
  templateUrl: './find-people.component.html',
  styleUrls: ['./find-people.component.css']
})
export class FindPeopleComponent implements OnInit {

  resume = [
    ['C# dev', 'Ревякін Олег Олегович', 'все вмію', 'рік досвіду', 'Англійська преінтермедіа', '', 'Інста']
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
