import { Component } from '@angular/core';
import { cinemasURL, mainHostForFiles } from '../commonConstants';
import { IBack } from '../interfaces/IBack';
import { IInputMode } from '../interfaces/IInputMode';
import { AjaxService } from '../service/ajax.service';
import { FileServiceForCinemasServices } from '../service/file.service';
import { MessageService } from '../service/message.service';
import { SeoBlockService } from '../service/seo-block-service.service';

@Component({
  selector: 'app-cinemas',
  templateUrl: './cinemas.component.html',
  // styleUrls: ['./cinemas.component.css']
  providers: [AjaxService, MessageService, FileServiceForCinemasServices]
})
export class CinemasComponent implements IInputMode, IBack {

  public inputMode: boolean = false
  public title: string = 'Cinemas'
  public infoForCinemasMainPage: Array<Array<string>> = []
  public logoImage: string = ''
  private logoImageFile: File = null
  public topBannerImage: string = ''
  private topBannerImageFile: File = null
  public images: Array<string> = []
  private imageFiles: Array<File> = []

  constructor(
    private ajax: AjaxService,
    private messageService: MessageService,
    private fileServiceForCinemasServices: FileServiceForCinemasServices
  ) {
    if (!this.inputMode) this.getCurrentCinemas()
  }

  getCurrentCinemas() {
    this.ajax.get(`${cinemasURL}/getAll?min_id=0`).subscribe(
      data => {
        this.infoForCinemasMainPage = []
        for (const [key, value] of Object.entries(data)) {
          let movieArr: Array<string> = [
            value['id'],                                          // 0
            value['name'],                                        // 1
            value['description'],                                 // 2
            value['about_cinema'],                                // 3
            value['conditions'],                                  // 4
            `${mainHostForFiles}/${value['path_to_logo_image']}`, // 5
            // value['path_to_top_banner_image'],                    // 6
          ]
          value['seo_block']                                    // 7
          this.infoForCinemasMainPage.push(movieArr)
        }
      },
      error => { }
    )
  }

  public openInpunMode(): void {
    this.inputMode = true
  }

  public closeInpunMode(): void {
    this.inputMode = false
  }

  public back(): void {
    this.closeInpunMode()
  }

  public openPhoto(event: any, index: number): void {
    if (event.target.files) {
      let render = new FileReader()
      let file: File = event.target.files[0]
      if (index == -2) { // logo
        render.onload = e => this.logoImage = e.target.result as string
        this.logoImageFile = file
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
    } else this.messageService.open('/assets/pngwing.com.png', 'Can\'t open image')
  }

  public removePhoto(index: number): void {
    if (index == -2) this.logoImage = ''
    else if (index == -1) this.topBannerImage = ''
    else this.images[index] = null
  }

  private addNewEntity(
    nameInput: string,
    descriptionTextarea: string,
    aboutCinemaTextarea: string,
    conditionsTextarea: string,
    seoResponse: any
  ): void {
    this.fileServiceForCinemasServices.processFileAndAddNewEntity(
      this.imageFiles,
      this.logoImageFile,
      nameInput,
      'logo',
      this.ajax,
      cinemasURL,
      this.topBannerImageFile,
      'top_banner',
      this.messageService,
      {
        name: nameInput,
        description: descriptionTextarea,
        aboutCinema: aboutCinemaTextarea,
        conditions: conditionsTextarea,
        seoBlockId: null
      },
      seoResponse,
      () => this.closeInpunMode()
    )
  }

  public submit(
    nameInput: string,
    descriptionTextarea: string,
    aboutCinemaTextarea: string,
    conditionsTextarea: string
  ) {
    if (nameInput != '' && this.logoImageFile != null)
      SeoBlockService.seoBlock.sendSeoBlock(
        (data: any) => {
          this.addNewEntity(
            nameInput,
            descriptionTextarea,
            aboutCinemaTextarea,
            conditionsTextarea,
            data
          )
        },
        () => {
          this.addNewEntity(
            nameInput,
            descriptionTextarea,
            aboutCinemaTextarea,
            conditionsTextarea,
            null
          )
        },
        this.messageService
      )
    else this.messageService.open('/assets/pngwing.com.png', 'Please, write cinema name and other fields with "*"')
  }

}