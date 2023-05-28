/**
 * 
 */
package org.springbootangular.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootangular.app.dto.UsuarioDTO;
import org.springbootangular.app.entities.Usuario;
import org.springbootangular.app.exceptions.ServiceException;
import org.springbootangular.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;

/**
 * @author jmedina
 *
 */
@Validated
@Service
public class UserService {
	
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository userRepository;

	public List<UsuarioDTO> findAll() throws ServiceException {
		
		List<UsuarioDTO> lista = new ArrayList<>(); 
		
		try {
    		List<Usuario> listaUsuarios = (List<Usuario>) userRepository.findAll();
        	listaUsuarios.forEach( usuario -> {
        		logger.debug( "User: {}", usuario );
        		lista.add( new UsuarioDTO(usuario) );
        	});
    	}  
		catch (Exception e) {
    		throw new ServiceException( e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE );
		}
		
		return lista;
	}
	
	public UsuarioDTO findById( Long id ) throws ServiceException {

		UsuarioDTO usuarioDTO = new UsuarioDTO();
    	try {
    		Optional<Usuario> usuario = userRepository.findById(id);
        	if( usuario.isPresent() ) {
        		usuarioDTO = new UsuarioDTO(usuario.get());
        	} else {
        		String msgError = "El Usuario no existe!!!";
        		throw new ServiceException( msgError, HttpStatus.BAD_REQUEST );
        	}
    	} catch(ServiceException se ) {
			throw se;
    	} catch (Exception e) {
    		throw new ServiceException( e.getMessage(), e, HttpStatus.SERVICE_UNAVAILABLE );
		}
    	
    	return usuarioDTO;
	}
	
	public UsuarioDTO save( @Valid Usuario usuario ) throws ServiceException {
		
    	try {
    		usuario = userRepository.save(usuario);
    	} catch( ValidationException e) {
    		throw new ServiceException( e.getMessage(), HttpStatus.CONFLICT );
		} catch (Exception e) {
    		throw new ServiceException( e.getMessage(), HttpStatus.BAD_REQUEST );
		}
    	
    	return new UsuarioDTO(usuario);
	}
	
	public void update( @Valid Usuario usuario ) throws ServiceException {
		
    	if( usuario.getId() != null && usuario.getId().longValue() > 0l ) {
    		try {
    			userRepository.update(usuario);
    		} catch (Exception e) {
    			throw new ServiceException( e.getMessage(), HttpStatus.BAD_REQUEST );
			}
    	} else {
    		String msgError = "No fue posible modificar el usuario sin ID!!!";
    		throw new ServiceException( msgError, HttpStatus.BAD_REQUEST );
    	}
	}
	
	public void delete( @NotNull Long id ) throws ServiceException { 

    	if( id > 0l ) {
    		try {
    			UsuarioDTO usuarioDTO = findById(id);
    			if( usuarioDTO != null ) {
    				userRepository.delete(usuarioDTO.transform());
    			} else {
    	    		String msgError = "No fue posible eliminar el usuario, no encontrado";
    	    		throw new ServiceException( msgError, HttpStatus.BAD_REQUEST );    				
    			}
    		} catch (Exception e) {
    			throw new ServiceException( e.getMessage(), HttpStatus.BAD_REQUEST );
			}
    	} else {
    		String msgError = "No fue posible eliminar el usuario sin ID!!!";
    		throw new ServiceException( msgError, HttpStatus.BAD_REQUEST );
    	}
	}

}
