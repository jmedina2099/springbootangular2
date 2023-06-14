import { Component } from '@angular/core';
import { FetchUsuariosService } from '../fetch-usuarios.service';
import { Usuario } from '../user/usuario';
import { ModalService } from '../modal-service.service';
import { LoggerService } from '../logger.service';

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

  constructor( private service: FetchUsuariosService, private modalService: ModalService,private loggerService: LoggerService) {}

  getListado() {
    this.service.fetchUsuarios()
      .subscribe( (data:Usuario[]) => {
        if( data ) {
          data.forEach( (usuario: Usuario) => {
            this.loggerService.log(JSON.stringify(usuario));
          });
          this.usuarios = data;
        }
      } );
  }

  validate() {
    return ( this.usuarioEditar.username && this.usuarioEditar.username !== '' &&
             !this.isCreateModal || (this.isCreateModal && this.usuarioEditar.password && this.usuarioEditar.password !== '') &&
             this.usuarioEditar.password === this.passwordConfirm &&
             this.usuarioEditar.email && this.usuarioEditar.email !== '' );
  }

  save() {
    this.loggerService.log('Guardar');
    if( this.validate() ) {
      if( this.isCreateModal ) {
        this.service.createUsuario(this.usuarioEditar)
        .subscribe( () => {
          this.loggerService.log('OK');
          this.modalService.close('modal-1');
          this.getListado();
        });
      } else {
        this.service.updateUsuario(this.usuarioEditar)
        .subscribe( () => {
          this.loggerService.log('OK');
          this.modalService.close('modal-1');
          this.getListado();
        });
      }
    } else {
      this.modalService.open('modal-3');
    }
  }

  delete() {
    this.loggerService.log('Borrar');
    if( this.usuarioBorrar ) {
      this.service.deleteUsuario(this.usuarioBorrar)
      .subscribe( () => {
        this.loggerService.log('OK');
        this.getListado();
      });
    }
    this.modalService.close('modal-2');
  }

  clean() {
    this.usuarios = undefined;
  }

  openModalCreate() {
    this.loggerService.log( 'openModalCreate') ;
    this.isCreateModal = true;
    this.usuarioEditar = { ...this.usuarioDefault };
    this.passwordConfirm = undefined;
    this.modalService.open('modal-1');
  }

  onPasswordChange() {
    if( this.usuarioEditar.password != this.passwordConfirm ) {
      this.loggerService.log( 'WRONG' )
      this.pwdInvalid = true;
    } else {
      this.pwdInvalid = false;
    }
  }

  openModalEdit(id: number) {
    this.loggerService.log( 'openModalEdit='+id) ;
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
    this.loggerService.log( 'openModalEliminar='+id) ;
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
    this.loggerService.log('Close modal');
    this.modalService.close(id);
  }

}
