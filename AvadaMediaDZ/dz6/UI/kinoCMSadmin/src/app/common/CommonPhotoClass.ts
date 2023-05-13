export class CommonPhotoClass {

    private _imageSRC: string = ''
    private _imageFile: File = null as File

    public constructor() { }

    public getImageSRC(): string {
        return this._imageSRC
    }

    public getImageFile(): File {
        return this._imageFile
    }

    public setImageSRC(arg: string) {
        this._imageSRC = arg
    }

    public setImageFile(arg: File) {
        this._imageFile = arg
    }

}