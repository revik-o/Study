import { Injectable } from '@angular/core';
import { SeoBlockComponent } from '../seo-block/seo-block.component';

@Injectable({
  providedIn: 'root'
})
export class SeoBlockService {

  public static seoBlock: SeoBlockComponent = null

}
