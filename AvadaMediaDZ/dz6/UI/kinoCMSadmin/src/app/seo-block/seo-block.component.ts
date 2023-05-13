import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { seoBlockURL } from '../commonConstants';
import { AjaxService } from '../service/ajax.service';
import { MessageService } from '../service/message.service';
import { SeoBlockService } from '../service/seo-block-service.service';

@Component({
  selector: 'app-seo-block',
  templateUrl: './seo-block.component.html',
  // styleUrls: ['./seo-block.component.css']
  providers: [AjaxService]
})
export class SeoBlockComponent {

  private seoUrl: string = ''
  private seoTitle: string = ''
  private seoKeyword: string = ''
  private seoDescription: string = ''

  constructor(private ajax: AjaxService) {
    SeoBlockService.seoBlock = this
  }

  public setSeoUrl(event: any): void {
    this.seoUrl = event.target.value
  }

  public setSeoTitle(event: any): void {
    this.seoTitle = event.target.value
  }

  public setSeoKeyword(event: any): void {
    this.seoKeyword = event.target.value
  }

  public setSeoDescription(event: any): void {
    this.seoDescription = event.target.value
  }

  public getSeoUrl(): string {
    return this.seoUrl
  }

  public getSeoTitle(): string {
    return this.seoTitle
  }

  public getSeoKeyword(): string {
    return this.seoKeyword
  }

  public getSeoDescription(): string {
    return this.seoDescription
  }

  public isValidate(): boolean {
    return this.seoUrl != '' || this.seoTitle != '' || this.seoKeyword != '' || this.seoDescription != ''
  }

  public createRequest(): Observable<object> {
    return this.ajax.post(`${seoBlockURL}/addNewSEOBlock`, {
      seoURL: this.seoUrl,
      seoTitle: this.seoTitle,
      seoKeyword: this.seoKeyword,
      seoDescription: this.seoDescription
    })
  }

  public sendSeoBlock(ifYesMethod: (data: any) => void, ifNoMethod: () => void, messageService: MessageService) {
    if (this.isValidate()) {
      this.createRequest().subscribe(
        data => {
          console.log(JSON.stringify(data))
          ifYesMethod(data as any)
        },
        error => {
          messageService.open('/assets/pngwing.com.png', 'Problems with request SEO Block')
        }
      )
    } else ifNoMethod()
  }

}