/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { PainelService } from './painel.service';

describe('Service: Painel', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PainelService]
    });
  });

  it('should ...', inject([PainelService], (service: PainelService) => {
    expect(service).toBeTruthy();
  }));
});
