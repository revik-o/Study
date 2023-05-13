import { Injectable } from '@angular/core';
import { MessageComponent } from '../message/message.component';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  private static messageComponent: MessageComponent = null

  public setErrorComponent(messageComponent: MessageComponent) {
    MessageService.messageComponent = messageComponent
  }

  public getDictionary(): Array<string> {
    return MessageService.messageComponent.idState4MainContainer
  }

  public open(imgPath: string, message: string) {
    MessageService.messageComponent.open()
    MessageService.messageComponent.imgPath = imgPath
    MessageService.messageComponent.message = message
  }

  public openNotify(imgPath: string, style: string, message: string) {
    MessageService.messageComponent.openNotify(style)
    MessageService.messageComponent.imgPath = imgPath
    MessageService.messageComponent.message = message
  }

}
