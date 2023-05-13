import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegCComponent } from './reg-c.component';

describe('RegCComponent', () => {
  let component: RegCComponent;
  let fixture: ComponentFixture<RegCComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegCComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
