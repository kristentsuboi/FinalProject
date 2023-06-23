import { TestBed } from '@angular/core/testing';

import { MedicalNoteService } from './medical-note.service';

describe('MedicalNoteService', () => {
  let service: MedicalNoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalNoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
