import { Component, HostListener, OnInit } from '@angular/core';
import { AjaxService } from './ajax.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [
    './app.component.scss', './app-change.component.scss',
    './app-load-point.component.scss', './app-error.component.scss'
  ],
  providers: [AjaxService]
})
export class AppComponent implements OnInit {

  @HostListener('window:load', ['$event']) EventListener(event: Event): void {
    document.getElementById("SplashScreen").style.opacity = "0";
    document.getElementById("SplashScreen").style.transition = "0.5s";
    setTimeout(() => document.getElementById("SplashScreen").style.visibility = "hidden", 505);
  };

  //STYLES
  header: string;
  block_content: string;
  main_page: string;
  load_point: string;
  header_: Array<string> = [
    'header', 'header-change', 'header-error'
  ];
  block_content_: Array<string> = [
    'block-content', 'block-content-change', 
    'block-content-error'
  ];
  main_page_: Array<string> = [
    'main-page', 'main-page-change'
  ];
  load_point_: Array<string> = [
    'load-point-disable', 'load-point-enable'
  ];


  title: string = 'Log-In';
  visibility_main_block: boolean = false;


  constructor(private ajax: AjaxService) {}

  ngOnInit() {
    this.header = this.header_[0];
    this.block_content = this.block_content_[0];
    this.main_page = this.main_page_[0];
    this.load_point = this.load_point_[0];
  }

  access_Log_In(header__:string, block_content__:string): void {
    this.header = header__;
    this.block_content = block_content__;
    setTimeout(() => {
      if (this.block_content === this.block_content_[1]) {
        this.title = 'Admin';
        this.visibility_main_block = true;
        this.main_page = this.main_page_[0];
      }else this.block_content = this.block_content_[0];
      this.load_point = this.load_point_[0];
      this.header = this.header_[0];
    }, 300);
  };

  log_in_click(login: string, password: string) {
    this.load_point = this.load_point_[1];
    let body = { 'login' : login, 'password' : password };
    const ajax_ = (index: number): void => {
      this.ajax.post(`${this.ajax.host[index]}/${this.ajax.api_url['log-in']}`, body).subscribe(
        data => {
          if (data['answer'] != 'none') this.access_Log_In(this.header_[1], this.block_content_[1]);
          else this.access_Log_In(this.header_[2], this.block_content_[2]);
        },
        error => {if (this.ajax.host.length < index) ajax_(index + 1)}
      );
    };
    ajax_(0);
  }

}
