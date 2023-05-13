import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogUComponent } from './log-u.component';

describe('LogUComponent', () => {
  let component: LogUComponent;
  let fixture: ComponentFixture<LogUComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogUComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
