import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { bannersPath, cinemasPath, hallPath, mailingPath, mainTitle, moviesPath, newsPath, pagesPath, stocksPath, usersPath } from '../commonConstants';
import { SessionService } from '../service/session.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  // styleUrls: ['./dashboard.component.css'],
  providers: [SessionService]
})
export class DashboardComponent implements OnInit {

  private classNonActivate: string = "nav-link"
  private classActivate: string = `${this.classNonActivate} active`

  public title: string = mainTitle
  //class for item
  public adminItems: Array<Array<string>> = [
    [this.classNonActivate, 'Banners', `/${bannersPath}`], [this.classNonActivate, 'Movies', `/${moviesPath}`],
    [this.classNonActivate, 'Cinemas', `/${cinemasPath}`], //[this.classNonActivate, 'News @Deprecated', `/${newsPath}`],
    //[this.classNonActivate, 'Stocks @Deprecated', `/${stocksPath}`], 
    [this.classNonActivate, 'Pages', `/${pagesPath}`],
    [this.classNonActivate, 'Users', `/${usersPath}`], [this.classNonActivate, 'Mailing', `/${mailingPath}`]
  ]

  public cinemasItems: Array<Array<string>> = [
    [this.classNonActivate, 'Hall', `/${hallPath}`],
    [this.classNonActivate, 'News', `/${newsPath}`], [this.classNonActivate, 'Stocks', `/${stocksPath}`],
  ]

  constructor(private sessionService: SessionService, private router: Router) {
    for (let i: number = 0; i < this.adminItems.length; i++)
      if (this.adminItems[i][2] === this.router.url) {
        this.activate(this.adminItems[i][1])
        break
      }
  }

  ngOnInit(): void {
  }

  public activate(name: string): void {
    switch (name) {
      case null:
        this.adminItems.forEach(value => {
          value[0] = this.classNonActivate
        })
        break
      default:
        this.adminItems.forEach(value => {
          if (value[1] === name) {
            value[0] = this.classActivate
            this.sessionService.getCurrentSession()
          } 
          else
            value[0] = this.classNonActivate
        })
        break
    }
  }

}
