import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import {
  complex_search,
  database_entity,
  page,
  simple_search,
  transaction_listener_state,
  transaction_pause_state,
  transaction_trotting_state,
} from "./types";

@Injectable({
  providedIn: "root",
})
export class HttpRequestService {
  private readonly HOST_PREFIX = location.host.startsWith("localhost:4200")
    ? "http://localhost:8080"
    : location.origin;

  public constructor(private readonly _http: HttpClient) {}

  private emptyBody(): any {
    return {};
  }

  public toggleToStartReceivingTransactions(): Observable<Object> {
    return this._http.put(
      `${this.HOST_PREFIX}/api/v1/transaction/listener/control/start`,
      this.emptyBody(),
    );
  }

  public toggleToStopReceivingTransactions(): Observable<Object> {
    return this._http.put(
      `${this.HOST_PREFIX}/api/v1/transaction/listener/control/stop`,
      this.emptyBody(),
    );
  }

  public checkReceivingTransactionsStatus(): Observable<transaction_listener_state> {
    return this._http.get<transaction_listener_state>(
      `${this.HOST_PREFIX}/api/v1/transaction/listener/control/read/state`,
    );
  }

  public readDbData(page: number): Observable<page<database_entity>> {
    return this._http.get<page<database_entity>>(
      `${this.HOST_PREFIX}/api/v1/transaction/access/read?page=${page}`,
    );
  }

  public sipleTransactionSearch(
    args: simple_search,
  ): Observable<page<database_entity>> {
    return this._http.post<page<database_entity>>(
      `${this.HOST_PREFIX}/api/v1/transaction/access/read/simply`,
      {
        searchBy: args.searchBy,
        value: args.value,
        page: args.page,
      },
    );
  }

  public complexTransactionSearch(
    args: complex_search,
  ): Observable<page<database_entity>> {
    return this._http.post<page<database_entity>>(
      `${this.HOST_PREFIX}/api/v1/transaction/access/read`,
      args,
    );
  }

  public enableTrotting(seconds: number): Observable<Object> {
    return this._http.put(
      `${this.HOST_PREFIX}/api/v1/transaction/trotting/control/start?seconds=${seconds}`,
      this.emptyBody(),
    );
  }

  public checkTrottingState(): Observable<transaction_trotting_state> {
    return this._http.get<transaction_trotting_state>(
      `${this.HOST_PREFIX}/api/v1/transaction/trotting/control/read/state`,
    );
  }

  public enablePause(seconds: number): Observable<Object> {
    return this._http.put(
      `${this.HOST_PREFIX}/api/v1/transaction/pause/control/start?seconds=${seconds}`,
      this.emptyBody(),
    );
  }

  public checkPauseState(): Observable<transaction_pause_state> {
    return this._http.get<transaction_pause_state>(
      `${this.HOST_PREFIX}/api/v1/transaction/pause/control/read/state`,
    );
  }
}
