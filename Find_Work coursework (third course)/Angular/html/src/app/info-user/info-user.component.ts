import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-info-user',
  templateUrl: './info-user.component.html',
  styleUrls: ['./info-user.component.css']
})
export class InfoUserComponent implements OnInit {

  email = 'oleg@gmail.com';
  place = [[1, 'Одеса'], [2, 'Київ'], [2, 'Львів']];
  resume = ['C#', 'Java', 'Python'];

  constructor() { }

  ngOnInit(): void {
  }

}
