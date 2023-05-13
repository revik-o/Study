import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-find-work',
  templateUrl: './find-work.component.html',
  styleUrls: ['./find-work.component.css']
})
export class FindWorkComponent implements OnInit {

  category = [[1, 'IT'], [2, 'HR']];

  experiense = [[1, 'без досвіду'], [2, 'менше року'], [3, 'більше року'], [4, 'бельше трьох років']]

  Order_By = ['Максимальна заробітна плата', 'Мінімальна заробітна плата'];

  place = [[1, 'Одеса'], [2, 'Київ'], [2, 'Львів']];

  job = [
    ['C# Dev', 'IT', 'KeepSolid', 'повна занятість', this.experiense[1][1], this.place[1][1], 'У нас погані презентації ' +
'Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptates, numquam assumenda minima quis, dolores quo consequuntur quasi cum non explicabo eligendi. Debitis sint molestias praesentium ut exercitationem consectetur non. Dolores?']
  ];

  constructor() { }

  ngOnInit(): void {
    this.JS();
  }

  JS() {}

}
