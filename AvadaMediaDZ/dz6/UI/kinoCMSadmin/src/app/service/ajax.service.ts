import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(private httpClient: HttpClient) { }

  public post(host_or_domain: string, body: {}): Observable<object> {
    return this.httpClient.post(host_or_domain, body, { headers: new HttpHeaders({ 'Content-type': 'application/json' }) })
  }

  public get(host_or_domain: string): Observable<object> {
    return this.httpClient.get(host_or_domain, { headers: new HttpHeaders({ 'Content-type': 'application/json' }) })
  }

  public getHttpClient(): HttpClient {
    return this.httpClient
  }

}
