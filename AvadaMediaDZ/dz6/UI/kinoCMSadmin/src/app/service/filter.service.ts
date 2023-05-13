import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class FilterService {

    public filterByIndex(index: number, argArray: Array<string>): Array<string> {
        let tempGallery: Array<string> = []
        for (let i = 0; i < argArray.length; i++)
            if (i != index)
                tempGallery[i] = argArray[i]
        return tempGallery
    }

}