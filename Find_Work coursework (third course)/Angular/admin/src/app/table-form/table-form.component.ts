import { Component, HostListener, Input, OnInit } from '@angular/core'
import { AjaxService } from '../ajax.service'
import { Table, Column } from '../INFO'

@Component({
  selector: 'app-table-form',
  templateUrl: './table-form.component.html',
  styleUrls: ['./table-form.component.scss', './table-form-change.component.scss'],
  providers: [AjaxService]
})
export class TableFormComponent implements OnInit {

  @Input() Data_Base_Name: string
  @Input() Table_Name: string

  @HostListener('window:resize') EventListenerResize(): void {
    document.getElementById('block-table')
          .style.maxHeight = `${document.getElementById('form').clientHeight / 1.3}px`;
  }

  scrollValue: number = 0

  table: string
  column: Array<string> = []
  key_column: Array<string> = []
  
  lines_table = []

  fields: Array<any>
  comparative_value: {}

  //STYLES
  option_menu: string = ''
  option_menu_: Array<string> = [
    'option-menu', 'option-menu-change'
  ]
  menu4fill: string = ''
  menu4fill_: Array<string> = [
    'menu-4-fill', 'menu-4-fill-change'
  ]
  load_screen: string = ''
  load_screen_: Array<string> = [
    'load-screen-off', 'load-screen-on'
  ]
  is_load: boolean

  constructor(private ajax: AjaxService) {
    this.is_load = false;
    this.load_screen = this.load_screen_[0];
  }

  EventListenerBlockTable(): void {
    document.getElementById('block-table').addEventListener('wheel', () => {
      document.getElementsByTagName('table')[0].style.transition = '0.3s';
      if (
        document.getElementById('block-table').scrollTop === 0 && 
          document.getElementById('block-table').scrollHeight != document.
            getElementById('block-table').clientHeight
      ) {
        document.getElementsByTagName('table')[0].style.transform 
          = `translateY(${this.scrollValue += 3}px)`;
        setTimeout(() => {
          document.getElementsByTagName('table')[0].style
            .transform = `translateY(${this.scrollValue = 0}px)`;
          document.getElementsByTagName('table')[0].style.transition = '0.5s';
        }, 300);
      } else if (
        (document.getElementById('block-table').scrollHeight - 
          document.getElementById('block-table').clientHeight) >=
            document.getElementById('block-table').scrollTop - 5 &&
              document.getElementById('block-table').scrollHeight != document.
                getElementById('block-table').clientHeight
      ) {
        document.getElementsByTagName('table')[0].style.transform 
          = `translateY(${this.scrollValue -= 3}px)`;
        setTimeout(() => {
          document.getElementsByTagName('table')[0].style
            .transform = `translateY(${this.scrollValue = 0}px)`;
          document.getElementsByTagName('table')[0].style.transition = '0.5s';
        }, 300);
      }
    });    
  }

  ngOnInit(): void {
    this.option_menu = this.option_menu_[0];
    this.menu4fill = this.menu4fill_[0];
    this.table = Table[this.Data_Base_Name][this.Table_Name];
    this.EventListenerBlockTable();
    for (let key of Object.keys(Column[this.Data_Base_Name][this.Table_Name])) {
      this.column.push(Column[this.Data_Base_Name][this.Table_Name][key]);
      this.key_column.push(key);
    }
    setTimeout(() => {
      document.getElementById(this.option_menu).style.visibility = 'hidden';
      document.getElementById(this.menu4fill).style.visibility = 'hidden';
      document.getElementById('block-table')
          .style.maxHeight = `${document.getElementById('form').clientHeight / 1.3}px`;
    }, 10); 
    this.lines(0);
  }

  password_exist(): Array<any> {
    let i = 0;
    for (let key of Object.keys(Column[this.Data_Base_Name][this.Table_Name])){
      if (key == 'password') return [true, i]; i += 1;
    }
    return [false];
  }

  lines(index: number): void {
    let body = {
      'query' : 'SELECT',
      'data base' : this.Data_Base_Name,
      'table' : this.Table_Name,
      'password exist' : this.password_exist()
    };
    if (this.ajax.host.length > index)
      this.ajax.post(`${this.ajax.host[index]}/${this.ajax.api_url['get table']}`, body).subscribe(
        data => {this.lines_table = data['lines']; this.columns4fill();}, 
          error => this.lines(index + 1));
  }

  columns4fill(): void {
    this.fields = [];
    if (this.Table_Name=='category'||this.Table_Name=='experience'||this.Table_Name=='place')
      this.fields = [['input', this.column[1], this.key_column[1], '']];
    else if (this.Table_Name == 'company') this.fields = 
      [
        ['input', this.column[1], this.key_column[1], ''],      // e_mail
        ['password', this.column[2], this.key_column[2]],       // password
        ['input', this.column[3], this.key_column[3], ''],      // company_name
        ['textarea', this.column[4], this.key_column[4], ''],   // description
        ['input', this.column[5], this.key_column[5], ''],      // photo_dir_
      ];
    else if (this.Table_Name == 'job') this.fields = 
      [
        ['input', this.column[1], this.key_column[1], ''],      // name_job
        ['select', this.column[2], this.key_column[2], []],     // company_id
        ['select', this.column[3], this.key_column[3], []],     // category_id
        ['select', this.column[4], this.key_column[4], []],     // place_id
        ['select', this.column[5], this.key_column[5], []],     // experience_id
        ['input', this.column[6], this.key_column[6], ''],      // salary
        ['textarea', this.column[7], this.key_column[7], ''],   // description
      ];
    else if (this.Table_Name == 'resume') this.fields = 
      [
        ['select', this.column[1], this.key_column[1], []],     // category_id
        ['select', this.column[2], this.key_column[2], []],     // user_id
        ['input', this.column[3], this.key_column[3], ''],      // name_resume
        ['textarea', this.column[4], this.key_column[4], ''],   // skills
        ['textarea', this.column[5], this.key_column[5], ''],   // experience
        ['textarea', this.column[6], this.key_column[6], ''],   // languages
        ['textarea', this.column[7], this.key_column[7], ''],   // description
        ['textarea', this.column[8], this.key_column[8], ''],   // find_me
      ];
    else this.fields = 
      [
        ['input', this.column[1], this.key_column[1], ''],      // e_mail
        ['password', this.column[2], this.key_column[2], ''],   // password
        ['input', this.column[3], this.key_column[3], ''],      // full_name
        ['input', this.column[4], this.key_column[4], '+380 '], // phone
        ['input', this.column[5], this.key_column[5], ''],      // photo_dir_
        ['select', this.column[6], this.key_column[6], []],     // place_id
      ];
    this.comparative_value = {};
    this.get_option(0);
  }

  get_option(index: number) : void {
    if (index < this.ajax.host.length)
      for (let i = 0; i < this.fields.length; i++)
        if (this.fields[i][2] == 'place_id' || this.fields[i][2] == 'user_id' || 
          this.fields[i][2] == 'category_id' || this.fields[i][2] == 'experience_id' || 
            this.fields[i][2] == 'company_id')
              this.ajax.post(`${this.ajax.host[index]}/${this.ajax.api_url['get options']}`, {'word' : this.fields[i][2]})
                .subscribe(data => data['option'].forEach(element => this.fields[i][3].push(element)), error => this.get_option(index + 1));
  }

  tr_click_flag: boolean = false;
  buff_line: Array<string>

  tr_click(line: Array<string>): void {
    this.tr_click_flag = !this.tr_click_flag;
    if (this.tr_click_flag) {
      let class_name: string = 'form';
      for (let i = 0; i < document.getElementsByClassName(class_name).length; i++)
      {(<HTMLElement>document.getElementsByClassName(class_name)[i]).style.filter = 'blur(1px)';
        (document.getElementsByClassName(class_name)[i] as HTMLElement).style.transition = '0.15s';}
      document.getElementById(this.option_menu).style.visibility = 'visible';
      setTimeout(() => this.option_menu = this.option_menu_[1], 0);
      this.buff_line = line;
      setTimeout(() => {
        for (let index: number = 0; index < line.length; index++) if (this.key_column[index]=='place_id' 
          || this.key_column[index]=='user_id' || this.key_column[index]=='category_id'
          || this.key_column[index]=='experience_id' || this.key_column[index]=='company_id') 
              this.comparative_value[this.key_column[index]] = line[index];
      }, 10);
      
    }else this.close_option_menu();
  }

  close_option_menu(): void {
    this.tr_click_flag = false;
    this.option_menu = this.option_menu_[0];
    let class_name: string = 'form';
    for (let i = 0; i < document.getElementsByClassName(class_name).length; i++)
    {(<HTMLElement>document.getElementsByClassName(class_name)[i]).style.filter = 'blur(0px)';
      (document.getElementsByClassName(class_name)[i] as HTMLElement).style.transition = '0.15s';}
    setTimeout(() => document.getElementById(this.option_menu)
          .style.visibility = 'hidden', 150);
    this.comparative_value = {};
  }

  name_button_menu4fill: string = ''

  open_menu4fill(button_name: string): void {
    document.getElementById(this.menu4fill).style.visibility = 'visible';
    setTimeout(() => this.menu4fill = this.menu4fill_[1], 1);
    this.name_button_menu4fill = button_name;
  }

  close_menu4fill(): void {
    this.menu4fill = this.menu4fill_[0];
    this.fields.forEach(element => (<HTMLInputElement>document.getElementById(element[2])).value = '');
    setTimeout(() => document.getElementById(this.menu4fill).style.visibility = 'hidden', 505);
  }

  open_update(): void {
    let i = 1;
    this.fields.forEach(element => {
      (<HTMLInputElement>document.getElementById(element[2])).value = 
        (element[2] != 'password') ? this.buff_line[i] : '';
      i += 1;
    });
    this.open_menu4fill('Відновити');
  }

  INSERT(info: Array<string>): {} {
    return {
      'query' : 'INSERT',
      'data base' : this.Data_Base_Name,
      'table' : this.Table_Name,
      'password exist' : this.password_exist(),
      'key_column' : this.key_column,
      'info' : info
    };
  }

  UPDATE(info: Array<string>): {} {
    return {
      'query' : 'UPDATE',
      'data base' : this.Data_Base_Name,
      'table' : this.Table_Name,
      'password exist' : this.password_exist(),
      'key_column' : this.key_column,
      'info' : info,
      'value id' : this.buff_line[0] // id
    };
  }

  DELETE(): void {
    this.load_ON();
    if (confirm('Are you shure?')){
      let body: {} = {
        'query' : 'DELETE',
        'data base' : this.Data_Base_Name,
        'table name' : this.Table_Name,
        'value id' : this.buff_line[0] // id
      };
      let ajax__ = this.ajax;
      function delete_(index : number): void {
        if (ajax__.host.length > index)
        ajax__.post(`${ajax__.host[index]}/${ajax__.api_url['get table']}`, body)
            .subscribe(data => {}, error => delete_(index + 1));
      };
      delete_(0);
    }
    setTimeout(() => this.lines(0), 300);
    this.close_menu4fill();
    this.load_OFF();
  }
  
  open_insert(): void { this.open_menu4fill('Відправити'); }

  subbmit(): void {
    this.load_ON();
    let info: Array<string> = ['none',];
    for (let index = 1; index < this.key_column.length; index++) {
      try {
        info.push(((<HTMLInputElement>document.getElementById(this.key_column[index])).value != '') ? 
        (<HTMLInputElement>document.getElementById(this.key_column[index])).value : 'none');
      }catch(e){
        info.push('none');
      }
    }
    let body = {};
    if (this.name_button_menu4fill == 'Відправити') body = this.INSERT(info); else body = this.UPDATE(info);
    const ajax__: (index: number) => void = index => {
      if (index < this.ajax.host.length)
        this.ajax.post(`${this.ajax.host[index]}/${this.ajax.api_url['get table']}`, body)
          .subscribe(data => {this.lines(0); this.load_OFF();}, error => ajax__(index + 1));
    };
    ajax__(0);
    this.close_menu4fill();
  }

  excel() {
    this.load_ON();
    this.ajax.post(`${this.ajax.host[0]}/${this.ajax.api_url['get excel']}`, {'table' : this.Table_Name}).subscribe(
      (data) => {
        var a = document.createElement('a');
        a.style.display = 'none';
        a.href = `data:${data['contentType']};base64,${data['base64']}`;
        a.download = `${this.Table_Name}.xls`;
        a.click()
        this.load_OFF();
      },
      error => {
        this.load_OFF();
      }
    );
    
  }

  load_ON(): void {
    this.is_load = true;
    setTimeout(() => this.load_screen = this.load_screen_[1], 20);
  }

  load_OFF(): void {
    setTimeout(() => this.load_screen = this.load_screen_[0], 30);
    setTimeout(() => this.is_load = false, 2000);
  }

}
