import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from './user/usuario';

@Injectable({
  providedIn: 'root'
})
export class FetchUsuariosService {
  private configUrl: string = 'http://localhost:8080/angularapp';

  constructor(private http: HttpClient) { }

  fetchUsuarios() {

    return this.http.get<Usuario[]>(this.configUrl + "/users" );

  }

  createUsuario(usuarioCrear: Usuario) {

    console.log("createUsuario");
    return this.http.post(this.configUrl + "/create",usuarioCrear );
  }
  
  updateUsuario(usuarioEditar: Usuario) {

    console.log("updateUsuario");
    return this.http.put(this.configUrl + "/update",usuarioEditar );
  }

  deleteUsuario(usuarioBorrar: Usuario) {
    console.log("deleteUsuario");
    return this.http.delete(this.configUrl + "/delete",{body: usuarioBorrar} );
  }

}
