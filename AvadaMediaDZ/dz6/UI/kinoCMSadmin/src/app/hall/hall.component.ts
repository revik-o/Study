import { Component } from '@angular/core';
import { CommonPhotoClass } from '../common/CommonPhotoClass';
import { IBack } from '../interfaces/IBack';
import { IInputMode } from '../interfaces/IInputMode';
import { IPhoto } from '../interfaces/IPhoto';
import { AjaxService } from '../service/ajax.service';
import { FileServiceForCinemasServices } from '../service/file.service';
import { FilterService } from '../service/filter.service';
import { MessageService } from '../service/message.service';
import { PhotoService } from '../service/photo.service';
import { SeoBlockService } from '../service/seo-block-service.service';
import { hallURL } from '../commonConstants'

@Component({
  selector: 'app-hall',
  templateUrl: './hall.component.html',
  styleUrls: ['./hall.component.css'],
  providers: [AjaxService, MessageService, FileServiceForCinemasServices, PhotoService, FilterService]
})
export class HallComponent implements IInputMode, IBack, IPhoto {

  public inputMode: boolean = false
  public title: string = 'Hall'
  public infoArrayMainPage: Array<Array<string>> = []
  public mainImage: string = ''
  private mainImageFile: File = null
  public topBannerImage: string = ''
  private topBannerImageFile: File = null
  public gallery: Array<string> = []
  private galleryFiles: Array<File> = []

  constructor(
    private ajax: AjaxService,
    private messageService: MessageService,
    private fileServiceForCinemasServices: FileServiceForCinemasServices,
    private photoService: PhotoService,
    private filterService: FilterService
  ) { }

  public openInpunMode() {
    this.inputMode = true
  }

  public closeInpunMode() {
    this.inputMode = false
  }

  public back() {
    this.closeInpunMode()
  }

  public openPhoto(event: any, index: number): void {
    this.photoService.when(
      event,
      this.messageService,
      index,
      (arg: CommonPhotoClass) => {
        this.mainImage = arg.getImageSRC()
        this.mainImageFile = arg.getImageFile()
      },
      (arg: CommonPhotoClass) => {
        this.topBannerImage = arg.getImageSRC()
        this.topBannerImageFile = arg.getImageFile()
      },
      (arg: CommonPhotoClass) => {
        this.gallery.push(arg.getImageSRC())
        this.galleryFiles.push(arg.getImageFile())
      },
    )

    /*if (event.target.files) {
      let render = new FileReader()
      let file: File = event.target.files[0]
      if (index == -2) { // logo
        render.onload = e => this.mainImage = e.target.result as string
        this.mainImageFile = file
      }
      else if (index == -1) { // top banner
        render.onload = e => this.topBannerImage = e.target.result as string
        this.topBannerImageFile = file
      }
      else { // gallery
        render.onload = e => this.images.push(e.target.result as string)
        this.imageFiles.push(file)
      }
      render.readAsDataURL(event.target.files[0])
    } else this.messageService.open('/assets/pngwing.com.png', 'Can\'t open image')*/
  }

  public removePhoto(index: number): void {
    if (index == -2)
      this.mainImage = ''
    else if (index == -1)
      this.topBannerImage = ''
    else
      this.gallery = this.filterService.filterByIndex(index, this.gallery)
  }

  private addNewEntity(numberInput: string, description: string, seoResponse: any): void {
    this.fileServiceForCinemasServices.processFileAndAddNewEntity(
      this.galleryFiles,
      this.mainImageFile,
      numberInput,
      'layout',
      this.ajax,
      hallURL,
      this.topBannerImageFile,
      'top_banner',
      this.messageService,
      {
        number: numberInput,
        description: description,
        cinemaId: 0, // TODO
        seoBlockId: null
      },
      seoResponse,
      () => {
        this.closeInpunMode()
      }
    )
  }

  public submit(numberInput: string, description: string): void {
    if (numberInput != '' && this.mainImage != '' && this.mainImageFile != null)
      SeoBlockService.seoBlock.sendSeoBlock(
        (data: any) => this.addNewEntity(
          numberInput,
          description,
          data
        ),
        () => this.addNewEntity(
          numberInput,
          description,
          null as any
        ),
        this.messageService
      )
    else this.messageService.open('/assets/pngwing.com.png', 'Please, write cinema name and other fields with "*"')
  }

}
