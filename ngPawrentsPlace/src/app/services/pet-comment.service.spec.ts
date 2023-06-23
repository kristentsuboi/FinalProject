import { TestBed } from '@angular/core/testing';

import { PetCommentService } from './pet-comment.service';

describe('PetCommentService', () => {
  let service: PetCommentService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetCommentService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
