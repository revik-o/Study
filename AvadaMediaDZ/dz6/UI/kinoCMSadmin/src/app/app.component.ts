import { Component } from '@angular/core';
import { mainTitle } from './commonConstants';
import { SessionService } from './service/session.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  // styleUrls: ['./app.component.css']
  providers: []
})
export class AppComponent {

  public title: string = mainTitle
  public inState: boolean = true

  constructor(private sessionService: SessionService) {
    sessionService.setMainAppComponent(this)
    sessionService.getCurrentSession()
  }

  public signIn(login: string, password: string): void {
    this.sessionService.setLoginAndPassword(login, password)
    this.sessionService.getCurrentSession()
  }

}
