import { TestBed } from '@angular/core/testing';

import { BusinessRatingService } from './business-rating.service';

describe('BusinessRatingService', () => {
  let service: BusinessRatingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BusinessRatingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
