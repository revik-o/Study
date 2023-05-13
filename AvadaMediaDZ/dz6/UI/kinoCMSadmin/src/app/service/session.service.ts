import { Injectable } from '@angular/core';
import { AppComponent } from '../app.component';
import { mainHost } from '../commonConstants';
import { AjaxService } from './ajax.service';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  constructor(private ajax: AjaxService, private errorService: MessageService) { }

  private static applicationIsStarted = false
  private static loginStr: string = 'admin'
  private static passwordStr: string = 'admin'
  private static mainAppComponent: AppComponent = null as AppComponent

  public static getLogin_s(): string {
    return this.loginStr
  } 

  public getLogin(): string {
    return SessionService.getLogin_s()
  }

  public setMainAppComponent(appComponent: AppComponent) {
    SessionService.mainAppComponent = appComponent
  }

  public switchLogInOn() {
    SessionService.mainAppComponent.inState = true
  }

  public switchLogInOff() {
    SessionService.mainAppComponent.inState = false
  }

  public setLoginAndPassword(login, password) {
    SessionService.loginStr = login
    SessionService.passwordStr = password
  }

  private requestFalse(): void {
    this.switchLogInOff()
    if (SessionService.applicationIsStarted)
      this.errorService.open('/assets/pngwing.com.png', 'You have mistake in login or password')
  }

  public getCurrentSession(): void {
    this.ajax.post(`${mainHost}/session`, {
      login: SessionService.loginStr,
      password: SessionService.passwordStr
    }).subscribe(
      data => {
        (data) ? this.switchLogInOn() : this.requestFalse()
        SessionService.applicationIsStarted = true
      },
      error => {
        this.errorService.open('/assets/pngwing.com.png', 'Problems with session')
        console.log(`${error} ${JSON.stringify(error)}`)
      }
    )
  }

}