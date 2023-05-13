import { Injectable } from "@angular/core";
import { AjaxService } from "./ajax.service";
import { MessageService } from "./message.service";

@Injectable({
    providedIn: 'root'
})
export class FileServiceForCinemasServices {

    public processFileAndAddNewEntity(
        imageFiles: Array<File>,
        mainImageFile: File,
        nameFilePrefix: string,
        nameFileSuffix: string,
        ajax: AjaxService,
        mainUrl: string,
        secondFileLikeTopBanner: File,
        secondFileSuffix: string,
        messageService: MessageService,
        argBody: any,
        seoResponse: any,
        closeInpunMode: () => void
    ) {
        let filteredImageFiles: Array<File> = []
        imageFiles.forEach(elem => {
            if (elem != null) filteredImageFiles.push(elem)
        })
        let formDataMainImage = new FormData()
        formDataMainImage.append('file', mainImageFile, `${nameFilePrefix}_${nameFileSuffix}_${mainImageFile.name}`)
        ajax.getHttpClient().post(`${mainUrl}/addPhoto`, formDataMainImage, { observe: 'response' }).subscribe(
            data => {
                if (secondFileLikeTopBanner != null as File && secondFileSuffix != null as string) {
                    let formData = new FormData()
                    formData.append('file', secondFileLikeTopBanner, `${nameFilePrefix}_${secondFileSuffix}_${secondFileLikeTopBanner.name}`)
                    ajax.getHttpClient().post(`${mainUrl}/addPhoto`, formData, { observe: 'response' }).subscribe(
                        data => { },
                        error => {
                            messageService.open('/assets/pngwing.com.png', `Problems with ${secondFileSuffix} of photo`)
                        }
                    )
                }
                if (data['body']) {
                    if (filteredImageFiles.length != 0) {
                        let index: number = 0;
                        filteredImageFiles.forEach(elem => {
                            ++index
                            let formData = new FormData()
                            formData.append('file', elem, `${nameFilePrefix}_${index}_${elem.name}`)
                            ajax.getHttpClient().post(`${mainUrl}/addPhoto`, formData, { observe: 'response' }).subscribe(
                                body => { },
                                error => {
                                    messageService.open('/assets/pngwing.com.png', `Problems with gallery of photo (index: ${index})`)
                                }
                            )
                        })
                        while (index < filteredImageFiles.length) {
                            // console.log(`${index} : ${filteredImageFiles.length}`)
                        }
                    }
                    let body: any = argBody
                    body.seoBlockId = (seoResponse != null) ? seoResponse : -1
                    ajax.post(`${mainUrl}/addNewEntity`, body).subscribe(
                        data => {
                            messageService.openNotify('/assets/pngarea.com_check-mark-icon-472478.png', messageService.getDictionary()[2], 'Good request')
                            closeInpunMode()
                        },
                        error => {
                            messageService.open('/assets/pngwing.com.png', 'Can\'t send data')
                        }
                    )
                }
            },
            error => {
                messageService.open('/assets/pngwing.com.png', 'Problems with request main photo')
            }
        )
    }

}