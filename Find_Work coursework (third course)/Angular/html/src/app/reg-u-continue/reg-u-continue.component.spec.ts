import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegUContinueComponent } from './reg-u-continue.component';

describe('RegUContinueComponent', () => {
  let component: RegUContinueComponent;
  let fixture: ComponentFixture<RegUContinueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegUContinueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegUContinueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
