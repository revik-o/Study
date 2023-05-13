import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reg-u-continue',
  templateUrl: './reg-u-continue.component.html',
  styleUrls: ['./reg-u-continue.component.css']
})
export class RegUContinueComponent implements OnInit {

  email = 'oleg@gmail.com';

  place = [[1, 'Одеса'], [2, 'Київ'], [1, 'Львів']];

  constructor() { }

  ngOnInit(): void {
  }

}
