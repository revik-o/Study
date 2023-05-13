import { HttpClient, HttpHeaders } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  private option: {} = {
    headers : new HttpHeaders(
      {
        'Content-type' : 'application/json'
      }
    )
  }

  public host: Array<string> = [
    'http://127.0.0.1:8000',
  ]

  // public port: Array<string> = [
  //   '8000', 
  // ]

  public api_url = {
    'log-in' : 'API/log-in/',
    'get table' : 'API/table/',
    'get options' : 'API/options/',
    'get excel' : 'API/exel/'
  }

  constructor(private http: HttpClient) { }

  public post(host_or_domain: string, body: {}): Observable<object> {
    return this.http.post(host_or_domain, body, this.option)
  }

  public get(host_or_domain: string): Observable<object> {
    return this.http.post(host_or_domain, this.option)
  }
  
}
