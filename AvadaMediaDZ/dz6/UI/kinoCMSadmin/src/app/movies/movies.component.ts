import { Component } from '@angular/core';
import { mainHostForFiles, movieURL } from '../commonConstants';
import { IBack } from '../interfaces/IBack';
import { IInputMode } from '../interfaces/IInputMode';
import { AjaxService } from '../service/ajax.service';
import { FileServiceForCinemasServices } from '../service/file.service';
import { MessageService } from '../service/message.service';
import { SeoBlockService } from '../service/seo-block-service.service';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css'],
  providers: [AjaxService, MessageService, FileServiceForCinemasServices]
})
export class MoviesComponent implements IInputMode, IBack {

  public inputMode: boolean = false
  public title: string = 'Movies'
  public imagesForMoviesMainPage: Array<Array<string>> = []
  public imagesForSoonMoviesMainPage: Array<Array<string>> = []
  public mainImage: string = ''
  public images: Array<string> = []
  private mainImageFile: File = null
  private imageFiles: Array<File> = []

  constructor(
    private ajax: AjaxService,
    private messageService: MessageService,
    private fileServiceForCinemasServices: FileServiceForCinemasServices
  ) {
    if (!this.inputMode) this.getCurrentMovies()
  }

  public openInpunMode(): void {
    this.mainImage = ''
    this.images = []
    this.mainImageFile = null
    this.imageFiles = []
    this.inputMode = true
    window.scrollTo(0, 0);
  }

  public closeInpunMode(): void {
    this.getCurrentMovies()
    this.inputMode = false
    window.scrollTo(0, 0);
  }

  public back(): void {
    this.closeInpunMode()
  }

  private getCurrentMovies() {
    this.ajax.get(`${movieURL}/getAll?min_id=0`).subscribe(
      data => {
        this.imagesForMoviesMainPage = []
        for (const [key, value] of Object.entries(data)) {
          let movieArr: Array<string> = [
            value['id'],                                          // 0
            value['name'],                                        // 1
            value['description'],                                 // 2
            `${mainHostForFiles}/${value['path_to_main_image']}`, // 3
            value['path_to_top_banner_image'],                    // 4
            value['seo_block']                                    // 5
          ]
          this.imagesForMoviesMainPage.push(movieArr)
        }
      },
      error => { }
    )
  }

  public openPhoto(event: any, index: number): void {
    if (event.target.files) {
      let render = new FileReader()
      let file: File = event.target.files[0] as File
      if (index != -1) {
        render.onload = e => this.images.push(e.target.result as string)
        this.imageFiles.push(file)
      }
      else {
        render.onload = e => this.mainImage = e.target.result as string
        this.mainImageFile = file
      }
      render.readAsDataURL(event.target.files[0])
    } else this.messageService.open('/assets/pngwing.com.png', 'Can\'t open image')
  }

  public removePhoto(index: number): void {
    if (index != -1) this.images[index] = null
    else this.mainImage = ''
  }

  private addNewMovie(
    nameInput: string,
    descriptionTextarea: string,
    seoResponse: any
  ) {
    this.fileServiceForCinemasServices.processFileAndAddNewEntity(
      this.imageFiles,
      this.mainImageFile,
      nameInput,
      'main',
      this.ajax,
      movieURL,
      null as File,
      null as string,
      this.messageService,
      {
        name: nameInput,
        description: descriptionTextarea,
        seoBlockId: null
      },
      seoResponse, 
      () => this.closeInpunMode()
    )
  }

  public submit(
    nameInput: string,
    descriptionTextarea: string
  ) {
    if (nameInput != '' && this.mainImage != '')
      SeoBlockService.seoBlock.sendSeoBlock(
        (data: any) => {
          this.addNewMovie(
            nameInput,
            descriptionTextarea,
            data
          )
        },
        () => {
          this.addNewMovie(
            nameInput,
            descriptionTextarea,
            null
          )
        },
        this.messageService
      )
    else this.messageService.open('/assets/pngwing.com.png', 'Please, write movie name and other info')
  }

}