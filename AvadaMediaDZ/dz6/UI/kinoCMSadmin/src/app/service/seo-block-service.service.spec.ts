import { TestBed } from '@angular/core/testing';

import { SeoBlockService } from './seo-block-service.service';

describe('SeoBlockServiceService', () => {
  let service: SeoBlockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SeoBlockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
