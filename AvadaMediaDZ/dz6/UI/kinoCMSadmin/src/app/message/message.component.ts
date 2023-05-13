import { Component } from '@angular/core';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent {

  public idState4MainContainer: Array<string> = ['main_container_close', 'main_container', 'main_container_message']

  public isOpen: boolean = false
  public imgPath: string = ''
  public mainContainer: string = this.idState4MainContainer[0]
  public message: string = ''

  constructor(private errorService: MessageService) {
    this.errorService.setErrorComponent(this)
  }

  public close() {
    this.mainContainer = this.idState4MainContainer[0]
      setTimeout(() => {
        this.isOpen = false
      }, 400);
  }

  public autoClose() {
    setTimeout(() => this.close(), 5000);
  }

  public open() {
    this.isOpen = true
    this.mainContainer = this.idState4MainContainer[1]
    this.autoClose()
  }

  public openNotify(str: string) {
    this.isOpen = true
    this.mainContainer = str
    this.autoClose()
  }

}
