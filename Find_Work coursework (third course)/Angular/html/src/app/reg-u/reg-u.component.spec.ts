import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegUComponent } from './reg-u.component';

describe('RegUComponent', () => {
  let component: RegUComponent;
  let fixture: ComponentFixture<RegUComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegUComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegUComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
