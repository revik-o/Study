import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BannersComponent } from './banners/banners.component';
import { CinemasComponent } from './cinemas/cinemas.component';
import { bannersPath, cinemasPath, hallPath, mailingPath, moviesPath, newsPath, pagesPath, stocksPath, usersPath } from './commonConstants';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HallComponent } from './hall/hall.component';
import { MailingComponent } from './mailing/mailing.component';
import { MoviesComponent } from './movies/movies.component';
import { NewsComponent } from './news/news.component';
import { PagesComponent } from './pages/pages.component';
import { StocksComponent } from './stocks/stocks.component';
import { UsersComponent } from './users/users.component';

const routes: Routes = [
  { path: '', component: DashboardComponent },
  { path: bannersPath, component: BannersComponent },
  { path: moviesPath, component: MoviesComponent },
  { path: cinemasPath, component: CinemasComponent },
  { path: newsPath, component: NewsComponent },
  { path: stocksPath, component: StocksComponent },
  { path: pagesPath, component: PagesComponent },
  { path: usersPath, component: UsersComponent },
  { path: mailingPath, component: MailingComponent },
  { path: hallPath, component: HallComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
