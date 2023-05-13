import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeoBlockComponent } from './seo-block.component';

describe('SeoBlockComponent', () => {
  let component: SeoBlockComponent;
  let fixture: ComponentFixture<SeoBlockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeoBlockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeoBlockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
