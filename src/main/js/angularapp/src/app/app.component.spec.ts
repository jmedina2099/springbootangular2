import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { ListadoComponent } from './listado/listado.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ModalComponent } from './modal/modal.component';
import { FormsModule } from '@angular/forms';

describe('AppComponent', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [RouterTestingModule,HttpClientTestingModule,FormsModule],
    declarations: [AppComponent,ListadoComponent,ModalComponent]
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'angularfront'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('angularfront');
  });

  it('should render buttons', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.btn1')?.textContent).toContain('Limpiar pantalla');
    expect(compiled.querySelector('.btn2')?.textContent).toContain('Mostrar usuarios');
  });
});
