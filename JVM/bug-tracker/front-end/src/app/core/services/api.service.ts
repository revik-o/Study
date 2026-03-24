import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private readonly baseUrl = '/api/v1';

  constructor(private readonly http: HttpClient) { }

  public get<T>(endpoint: string, params?: HttpParams | { [param: string]: string | number | boolean | ReadonlyArray<string | number | boolean> }): Observable<T> {
    return this.http.get<T>(`${this.baseUrl}${endpoint}`, { params });
  }

  public post<T>(endpoint: string, body: any, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.post<T>(`${this.baseUrl}${endpoint}`, body, options);
  }

  public put<T>(endpoint: string, body: any, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.put<T>(`${this.baseUrl}${endpoint}`, body, options);
  }

  public delete<T>(endpoint: string, options?: { headers?: HttpHeaders }): Observable<T> {
    return this.http.delete<T>(`${this.baseUrl}${endpoint}`, options);
  }
}
