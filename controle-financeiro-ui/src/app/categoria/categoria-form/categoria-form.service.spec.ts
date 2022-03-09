import { TestBed } from '@angular/core/testing';

import { CategoriaFormService } from './categoria-form.service';

describe('CategoriaFormService', () => {
  let service: CategoriaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CategoriaFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
