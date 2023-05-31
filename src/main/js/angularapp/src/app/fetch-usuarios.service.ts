import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './user/usuario';
import { LoggerService } from './logger.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FetchUsuariosService {
  private configUrl: string = environment.backEndUrl;

  constructor(private http: HttpClient,private loggerService: LoggerService) { }

  fetchUsuarios() {
    return this.http.get<Usuario[]>(this.configUrl + "/users" );
  }

  createUsuario(usuarioCrear: Usuario) {
    this.loggerService.log("createUsuario");
    return this.http.post(this.configUrl + "/create",usuarioCrear );
  }
  
  updateUsuario(usuarioEditar: Usuario) {
    this.loggerService.log("updateUsuario");
    return this.http.put(this.configUrl + "/update",usuarioEditar );
  }

  deleteUsuario(usuarioBorrar: Usuario) {
    this.loggerService.log("deleteUsuario");
    return this.http.delete(this.configUrl + "/delete",{body: usuarioBorrar} );
  }

}
