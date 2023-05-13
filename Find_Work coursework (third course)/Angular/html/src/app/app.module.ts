import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FindWorkComponent } from './find-work/find-work.component';
import { LogUComponent } from './log-u/log-u.component';
import { RegUComponent } from './reg-u/reg-u.component';
import { RegUContinueComponent } from './reg-u-continue/reg-u-continue.component';
import { RegCComponent } from './reg-c/reg-c.component';
import { RegCContinueComponent } from './reg-c-continue/reg-c-continue.component';
import { LogCComponent } from './log-c/log-c.component';
import { AddNewJobComponent } from './add-new-job/add-new-job.component';
import { AddNewResumeComponent } from './add-new-resume/add-new-resume.component';
import { FindPeopleComponent } from './find-people/find-people.component';
import { InfoUserComponent } from './info-user/info-user.component';
import { PDFComponent } from './pdf/pdf.component';

@NgModule({
  declarations: [
    AppComponent,
    FindWorkComponent,
    LogUComponent,
    RegUComponent,
    RegUContinueComponent,
    RegCComponent,
    RegCContinueComponent,
    LogCComponent,
    AddNewJobComponent,
    AddNewResumeComponent,
    FindPeopleComponent,
    InfoUserComponent,
    PDFComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
