import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogCComponent } from './log-c.component';

describe('LogCComponent', () => {
  let component: LogCComponent;
  let fixture: ComponentFixture<LogCComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LogCComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LogCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
