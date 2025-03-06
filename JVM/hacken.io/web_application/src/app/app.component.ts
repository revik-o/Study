import { Component, OnInit } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { TablePageComponent } from "./table-page/table-page.component";
import { HttpRequestService } from "./http-request.service";
import { FormsModule } from "@angular/forms";

@Component({
  selector: "app-root",
  standalone: true,
  imports: [RouterOutlet, TablePageComponent, FormsModule],
  templateUrl: "./app.component.html",
  styleUrl: "./app.component.css",
})
export class AppComponent implements OnInit {
  private readonly REQUEST_SPACE = 300;
  public seconds: number = 0;
  public trotting: boolean = false;
  public toggle: boolean = false;
  public pause: boolean = false;
  private trottingIntervalId: any = null;
  private pauseIntervalId: any = null;

  public constructor(
    private readonly _httpRequestService: HttpRequestService,
  ) {}

  private async clearTrottingInterval(): Promise<void> {
    clearInterval(this.trottingIntervalId);
  }

  private async clearPauseInterval(): Promise<void> {
    clearInterval(this.pauseIntervalId);
  }

  private async findSplashScreen(): Promise<HTMLElement> {
    const result = document.getElementById("splash-screen-block");

    if (result) {
      return result;
    }

    throw new Error("splash-screen not found");
  }

  private handleErrors(error: any): void {
    if (!error.status || error.status >= 400) {
      alert("Error: " + error.message);
    }

    console.log(error);
  }

  public ngOnInit(): void {
    this._httpRequestService.checkReceivingTransactionsStatus().subscribe({
      next: (result) => {
        this.toggle = result.enabled;
        this.findSplashScreen().then((element) => {
          this._httpRequestService.checkTrottingState().subscribe({
            next: (trotting) => {
              this.handleTrottingResponse();
              this._httpRequestService.checkPauseState().subscribe({
                next: (pause) => {
                  this.handlePauseResponse();
                  element.classList.add("close-splash-screen");
                  setTimeout(() => element.remove(), 510);
                },
              });
            },
          });
        });
      },
      error: this.handleErrors,
    });
  }

  public onToggleChange(target: any): void {
    this.toggle = target.checked;

    this.toggle
      ? this._httpRequestService
          .toggleToStartReceivingTransactions()
          .subscribe({
            error: (error) => {
              if (!error.status || error.status >= 400) {
                this.handleErrors(error);
                this.toggle = !this.toggle;
              }
            },
          })
      : this._httpRequestService.toggleToStopReceivingTransactions().subscribe({
          error: (error) => {
            if (!error.status || error.status >= 400) {
              this.handleErrors(error);
              this.toggle = !this.toggle;
            }
          },
        });
  }

  private handleTrottingResponse(): void {
    this.trotting = true;
    this.trottingIntervalId = setInterval(
      () =>
        this._httpRequestService.checkTrottingState().subscribe({
          next: (result) => {
            this.trotting = result.isTrotting;

            if (!this.trotting) {
              this.clearTrottingInterval();
            }
          },
        }),
      this.REQUEST_SPACE,
    );
  }

  public enableTrotting(): void {
    if (this.seconds > 0 && !this.trotting) {
      this._httpRequestService.enableTrotting(this.seconds).subscribe({
        error: (error) => {
          if (!error.status || error.status >= 400) {
            this.handleErrors(error);
          } else {
            this.handleTrottingResponse();
          }
        },
      });
    }
  }

  private handlePauseResponse(): void {
    this.pause = true;
    this.pauseIntervalId = setInterval(
      () =>
        this._httpRequestService.checkPauseState().subscribe({
          next: (result) => {
            this.pause = result.isActivated;

            if (!this.pause) {
              this.clearPauseInterval();
            }
          },
        }),
      this.REQUEST_SPACE,
    );
  }

  public enablePause() {
    if (this.seconds > 0 && !this.pause) {
      this._httpRequestService.enablePause(this.seconds).subscribe({
        error: (error) => {
          if (!error.status || error.status >= 400) {
            this.handleErrors(error);
          } else {
            this.handlePauseResponse();
          }
        },
      });
    }
  }
}
