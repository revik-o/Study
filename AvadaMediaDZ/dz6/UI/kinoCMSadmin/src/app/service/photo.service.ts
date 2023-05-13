import { Injectable } from "@angular/core";
import { CommonPhotoClass } from "../common/CommonPhotoClass";
import { MessageService } from "./message.service";

@Injectable({
    providedIn: 'root'
})
export class PhotoService {

    public renderPhotoFile(event: any, messageService: MessageService): CommonPhotoClass {
        if (event.target.files) {
            let render = new FileReader()
            let file: File = event.target.files[0]
            let renderedPhoto = new CommonPhotoClass()
            render.onload = e => renderedPhoto.setImageSRC(e.target.result as string)
            renderedPhoto.setImageFile(file)
            return renderedPhoto
        } else {
            messageService.open('/assets/pngwing.com.png', 'Can\'t open image')
            return null as CommonPhotoClass
        }
    }

    public when(
        event: any,
        messageService: MessageService,
        index: number,
        caseOne: (arg: CommonPhotoClass) => void,
        caseTwo: (arg: CommonPhotoClass) => void,
        caseDefault: (arg: CommonPhotoClass) => void,
    ): void {
        let photo: CommonPhotoClass = this.renderPhotoFile(event, messageService)
        switch (index) {
            case -2:
                caseOne(photo)
                break
            case -1:
                caseTwo(photo)
                break
            default:
                caseDefault(photo)
                break
        }
    }

}