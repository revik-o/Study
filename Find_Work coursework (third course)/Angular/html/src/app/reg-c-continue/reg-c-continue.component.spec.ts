import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegCContinueComponent } from './reg-c-continue.component';

describe('RegCContinueComponent', () => {
  let component: RegCContinueComponent;
  let fixture: ComponentFixture<RegCContinueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegCContinueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegCContinueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
