import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-new-job',
  templateUrl: './add-new-job.component.html',
  styleUrls: ['./add-new-job.component.css']
})
export class AddNewJobComponent implements OnInit {

  category = [[1, 'IT'], [2, 'IT2'], [3, 'IT3']];
  company = 'KeepSolid';
  // employment
  experiense = [[1, '<1'], [2, '1'], [3, '1>']];
  place = [[1, 'Одеса'], [2, 'Київ'], [3, 'Львів']];

  constructor() { }

  ngOnInit(): void {
  }

}
