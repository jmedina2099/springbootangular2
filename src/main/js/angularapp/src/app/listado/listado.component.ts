import { Component } from '@angular/core';
import { FetchUsuariosService } from '../fetch-usuarios.service';
import { Usuario } from '../user/usuario';
import { ModalService } from '../modal-service.service';

@Component({
  selector: 'app-listado',
  templateUrl: './listado.component.html',
  styleUrls: ['./listado.component.css']
})
export class ListadoComponent {

  bodyText = 'This text can be updated in modal 1';
  
  usuarios: Usuario[] | undefined;
  passwordConfirm: string | undefined;

  isCreateModal: boolean = false;
  pwdInvalid: boolean = false;

  usuarioDefault: Usuario = {
    id: 0,
    username: '',
    password: '',
    firstName: '',
    middleName: '',
    lastName: '',
    email: '',
  };

  usuarioEditar: Usuario = { ...this.usuarioDefault };

  usuarioBorrar: Usuario = { ...this.usuarioDefault };

  constructor( private service: FetchUsuariosService, private modalService: ModalService) {}

  getListado() {
    this.service.fetchUsuarios()
      .subscribe( (data:Usuario[]) => {
        console.log(data);
        this.usuarios = data;
      } );
  }

  validate() {
    return ( this.usuarioEditar.username && this.usuarioEditar.username !== '' &&
             !this.isCreateModal || (this.isCreateModal && this.usuarioEditar.password && this.usuarioEditar.password !== '') &&
             this.usuarioEditar.password === this.passwordConfirm &&
             this.usuarioEditar.email && this.usuarioEditar.email !== '' );
  }

  save() {
    console.log('Guardar');
    if( this.validate() ) {
      if( this.isCreateModal ) {
        this.service.createUsuario(this.usuarioEditar)
        .subscribe( () => {
          console.log('OK');
          this.modalService.close('modal-1');
          this.getListado();
        });
      } else {
        this.service.updateUsuario(this.usuarioEditar)
        .subscribe( () => {
          console.log('OK');
          this.modalService.close('modal-1');
          this.getListado();
        });
      }
    } else {
      this.modalService.open('modal-3');
    }
  }

  delete() {
    console.log('Borrar');
    if( this.usuarioBorrar ) {
      this.service.deleteUsuario(this.usuarioBorrar)
      .subscribe( () => {
        console.log('OK');
        this.getListado();
      });
    }
  }

  clean() {
    this.usuarios = undefined;
  }

  openModalCreate() {
    console.log( 'openModalCreate') ;
    this.isCreateModal = true;
    this.usuarioEditar = { ...this.usuarioDefault };
    this.passwordConfirm = undefined;
    this.modalService.open('modal-1');
  }

  onPasswordChange() {
    if( this.usuarioEditar.password != this.passwordConfirm ) {
      console.log( 'WRONG' )
      this.pwdInvalid = true;
    } else {
      this.pwdInvalid = false;
    }
  }

  openModalEdit(id: number) {
    console.log( 'openModalEdit='+id) ;
    this.isCreateModal = false;
    if( this.usuarios ) {
      this.usuarios.forEach( usuario => {
        if( usuario.id === id ) {
          this.usuarioEditar = usuario;
        }
      });
    }
    this.modalService.open('modal-1');
  }

  openModalEliminar(id: number) {
    console.log( 'openModalEliminar='+id) ;
    if( this.usuarios ) {
      this.usuarios.forEach( usuario => {
        if( usuario.id === id ) {
          this.usuarioBorrar = usuario;
        }
      });
    }
    this.modalService.open('modal-2');
  }

  closeModal(id: string) {
    console.log('Close modal');
    this.modalService.close(id);
  }

}
