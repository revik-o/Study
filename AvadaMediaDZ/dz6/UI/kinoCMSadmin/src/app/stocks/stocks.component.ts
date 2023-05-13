import { Component, OnInit } from '@angular/core';
import { IBack } from '../interfaces/IBack';
import { AjaxService } from '../service/ajax.service';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  // styleUrls: ['./stocks.component.css']
  providers: [AjaxService, MessageService]
})
export class StocksComponent implements OnInit, IBack {

  public tempNewsState: boolean = true

  public inputMode: boolean = !false
  public title: string = 'Stocks'
  public imagesForCinemasMainPage: Array<Array<string>> = []

  public checkboxText: string = ''

  constructor(private ajax: AjaxService, private messageService: MessageService) { }

  ngOnInit(): void {
    this.checkboxSwitch(this.tempNewsState)
  }

  public back() { }

  public checkboxSwitch(state: boolean) {
    if (state) 
      this.checkboxText = 'Enable stocks'
    else 
      this.checkboxText = 'Disable stocks'
  }

}
