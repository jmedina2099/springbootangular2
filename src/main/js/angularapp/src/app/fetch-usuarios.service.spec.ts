import { TestBed } from '@angular/core/testing';

import { FetchUsuariosService } from './fetch-usuarios.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('FetchUsuariosService', () => {
  let service: FetchUsuariosService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(FetchUsuariosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
