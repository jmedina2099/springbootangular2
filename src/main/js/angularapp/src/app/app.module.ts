import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListadoComponent } from './listado/listado.component';
import { FetchUsuariosService } from './fetch-usuarios.service';
import { ModalComponent } from './modal/modal.component';
import { FormsModule } from '@angular/forms';
import { ModalService } from './modal-service.service';
import { LoggerService } from './logger.service';

@NgModule({
  declarations: [
    AppComponent,
    ListadoComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [FetchUsuariosService,ModalService,LoggerService],
  bootstrap: [AppComponent]
})
export class AppModule { }
