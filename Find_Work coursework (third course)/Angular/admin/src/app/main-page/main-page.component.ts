import { Component, OnInit } from '@angular/core'
import { Data_Base, Table } from '../INFO'

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss', './main-page-change.component.scss']
})
export class MainPageComponent implements OnInit {

  //STYLES
  block_table_form: string
  block_table_form_: Array<string> = [
    'block-table-form', 'block-table-form-open'
  ]
  block_table_form_visible: boolean = false


  Data_Bases_table_form: string = ''
  Table_Name_table_form: string = ''

  data_bases: () => Array<string> = () => {
    let temp_array: Array<string> = [];
    Object.keys(Data_Base).forEach( key => temp_array.push(Data_Base[key]) );
    return temp_array;
  }
  tables: (data_base_name: string) => Array<string> = (data_base_name: string) => {
    let temp_array: Array<string> = [];
    for(let key_Data_Base of Object.keys(Data_Base)){
      if(Data_Base[key_Data_Base] == data_base_name) {
        Object.keys(Table[key_Data_Base]).forEach( key_Table => {
          temp_array.push(Table[key_Data_Base][key_Table]);
        }); 
        break;
      }
    };
    return temp_array;
  }

  constructor() {
    this.block_table_form = this.block_table_form_[0];
  }

  ngOnInit(): void {
    document.getElementsByTagName('title')[0].innerText = 'Admin';
  }

  close_table_form(): void {
    this.block_table_form = this.block_table_form_[0];
    setTimeout(() => this.block_table_form_visible = false, 300);
  }

  table_row_click(data_base_name: string, table_name: string): void {
    this.Data_Bases_table_form = Object.keys(Data_Base)
      .find( key => Data_Base[key] === data_base_name);
    this.Table_Name_table_form = Object.keys(Table[this.Data_Bases_table_form])
      .find( key => Table[this.Data_Bases_table_form][key] === table_name);
    this.block_table_form_visible = true;
    setTimeout(() => this.block_table_form = this.block_table_form_[1], 100);
  }

}
