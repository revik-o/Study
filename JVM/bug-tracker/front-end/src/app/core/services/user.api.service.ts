import { Injectable } from "@angular/core";
import { ApiService } from "./api.service";
import { RegisterRequest } from "../models/auth.model";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root',
    deps: [ApiService]
})
export class UserApiService {

    constructor(private readonly apiService: ApiService) { }

    public register(user: RegisterRequest): Observable<void> {
        return this.apiService.post<void>('/user/register', user);
    }
}