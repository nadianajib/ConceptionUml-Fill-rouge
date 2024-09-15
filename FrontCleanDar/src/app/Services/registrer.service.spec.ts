import { TestBed } from '@angular/core/testing';

import { RegistrerService } from './registrer.service';

describe('RegistrerService', () => {
  let service: RegistrerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegistrerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
