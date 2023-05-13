import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewResumeComponent } from './add-new-resume.component';

describe('AddNewResumeComponent', () => {
  let component: AddNewResumeComponent;
  let fixture: ComponentFixture<AddNewResumeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddNewResumeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewResumeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
