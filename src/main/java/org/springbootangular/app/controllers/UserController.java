/**
 * 
 */
package org.springbootangular.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootangular.app.dto.UsuarioDTO;
import org.springbootangular.app.entities.Usuario;
import org.springbootangular.app.exceptions.ServiceException;
import org.springbootangular.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.ValidationException;

/**
 * @author jmedina
 *
 */
@RestController
@RequestMapping("/${context.path}")
public class UserController {
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
    @GetMapping("/users")
    public ResponseEntity<List<UsuarioDTO>> getUsers() {
    	
    	logger.info( "Getting users.." );
    	
    	List<UsuarioDTO> lista = new ArrayList<>();
		try {
    		lista = this.userService.findAll();
    	} catch (ServiceException e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>( e.getHttpStatus() );
		} catch (Exception e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>( HttpStatus.SERVICE_UNAVAILABLE );
		}

    	return new ResponseEntity<>( lista, HttpStatus.OK );
    }
    
    @GetMapping("/get")
    public ResponseEntity<UsuarioDTO> getUser(@RequestParam Long id) {
    	
    	UsuarioDTO usuarioDTO = new UsuarioDTO();
    	
    	try {
    		usuarioDTO = this.userService.findById(id);
    	} catch (ServiceException e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>( e.getHttpStatus() );
		} catch (Exception e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
    
    	return new ResponseEntity<>( usuarioDTO, HttpStatus.OK );
    }

    @PostMapping("/create")
    public synchronized ResponseEntity<UsuarioDTO> createUser( @RequestBody UsuarioDTO usuarioDTO) {

    	logger.info( "Create user.." );

    	try {
    		Usuario usuario = usuarioDTO.transform();
    		usuarioDTO = this.userService.save(usuario);
    	} catch (ServiceException e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>( e.getHttpStatus() );    		
		} catch (Exception e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}
    	
    	return new ResponseEntity<>( usuarioDTO, HttpStatus.OK );
    }

    @PutMapping("/update")
    public synchronized ResponseEntity<UsuarioDTO> modifyUser( @RequestBody UsuarioDTO usuarioDTO) {
    	
    	logger.info( "Update user.." );
    	
		try {
			Usuario usuario = usuarioDTO.transform();
			usuario.setPassword( "XXX" ); // Update doesn't affect password.
			this.userService.update(usuario);
		} catch( ValidationException e) {
			logger.error( "CONTROLLER: "+ e.getMessage(),e );
    		return new ResponseEntity<>( HttpStatus.CONFLICT );
		} catch (ServiceException e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>( e.getHttpStatus() );    		
		} catch (Exception e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}

    	return new ResponseEntity<>( HttpStatus.OK );
    }
    
    @DeleteMapping("/delete")
    public synchronized ResponseEntity<UsuarioDTO> deleteUser( @RequestBody UsuarioDTO usuarioDTO) {

    	logger.info( "Delete user.." );
    	
    	try {
			this.userService.delete(usuarioDTO.getId());
		} catch (ServiceException e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>( e.getHttpStatus() );    		
		} catch (Exception e) {
    		logger.error( e.getMessage(),e );
    		return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
		}

    	return new ResponseEntity<>( HttpStatus.OK);
    }
    
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
    	logger.error( ex.getMessage(),ex );
    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
