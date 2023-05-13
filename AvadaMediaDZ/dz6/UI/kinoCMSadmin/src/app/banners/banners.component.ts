import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../service/ajax.service';
import { MessageService } from '../service/message.service';

@Component({
  selector: 'app-banners',
  templateUrl: './banners.component.html',
  // styleUrls: ['./banners.component.css']
  providers: [AjaxService, MessageService]
})
export class BannersComponent implements OnInit {

  public images: Array<string> = []
  private imageFiles: Array<File> = []
  public backgroundImage: string = ''
  private backgroundImageFile: File = null

  constructor(private ajax: AjaxService, private messageService: MessageService) { }

  ngOnInit(): void {
  }

  getCurrentData() {
  }

  public openPhoto(event: any, index: number): void {
    if (event.target.files) {
      let render = new FileReader()
      let file: File = event.target.files[0]
      if (index == -2) { // Background
        render.onload = e => this.backgroundImage = e.target.result as string
        this.backgroundImageFile = file
      }
      else if (index == -1) { }
      else { // gallery
        render.onload = e => this.images.push(e.target.result as string)
        this.imageFiles.push(file)
      }
      render.readAsDataURL(event.target.files[0])
    } else this.messageService.open('/assets/pngwing.com.png', 'Can\'t open image')
  }

  public removePhoto(index: number): void {
    if (index == -2) this.backgroundImage = ''
    // else if (index == -1) this.topBannerImage = ''
    else this.images[index] = null
  }

}
