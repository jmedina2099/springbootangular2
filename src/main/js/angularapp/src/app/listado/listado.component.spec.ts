import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoComponent } from './listado.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ModalComponent } from '../modal/modal.component';
import { FormsModule } from '@angular/forms';

describe('ListadoComponent', () => {
  let component: ListadoComponent;
  let fixture: ComponentFixture<ListadoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListadoComponent,ModalComponent],
      imports: [HttpClientTestingModule,FormsModule]
    });
    fixture = TestBed.createComponent(ListadoComponent);
    component = fixture.componentInstance;  
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
