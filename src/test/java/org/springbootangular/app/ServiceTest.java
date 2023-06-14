/**
 * 
 */
package org.springbootangular.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springbootangular.app.dto.UsuarioDTO;
import org.springbootangular.app.entities.Usuario;
import org.springbootangular.app.exceptions.ServiceException;
import org.springbootangular.app.repositories.UserRepository;
import org.springbootangular.app.services.UserService;

/**
 * @author jmediros
 *
 */
@ExtendWith(MockitoExtension.class)
public class ServiceTest {

	
	@InjectMocks
	private UserService business;

	@Mock
	private UserRepository repository;

	@Test
	public void retrieveAllItems_basic() {
		
		Usuario u1 = new Usuario();
		u1.setId(1l);
		u1.setUsername("admin");

		Usuario u2= new Usuario();
		u2.setId(2l);
		u2.setUsername("user");

		when(repository.findAll()).thenReturn(Arrays.asList(u1,u2));
		
		List<UsuarioDTO> items = null;
		try {
			items = business.findAll();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
        assertEquals(2,items.size());
        
        UsuarioDTO user1 = items.get(0);
        assertEquals(1,user1.getId());
        
        assertEquals("admin",user1.getUsername());
        
        UsuarioDTO user2 = items.get(1);
        assertEquals(2,user2.getId());
        
        assertEquals("user",user2.getUsername());
	}
	
	@Test
	public void retrieveByID() {
		
		Usuario u1 = new Usuario();
		u1.setId(1l);
		u1.setUsername("admin");
		
		when(repository.findById(1l)).thenReturn(Optional.of(u1));
		
		UsuarioDTO usuarioDTO = null;
		try {
			usuarioDTO = business.findById(1l);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

        assertEquals(1,usuarioDTO.getId());
        assertEquals("admin",usuarioDTO.getUsername());
	}
	
	@Test
	public void save() {
		
		Usuario usuario = new Usuario();
		usuario.setUsername( "jmedina" );
		usuario.setPassword( "hola123" );
		usuario.setEmail( "medinarosas.jorgealberto@gmail.com" );
		
		when(repository.save(usuario)).thenReturn(usuario);

		UsuarioDTO usuarioDTO = null;
		try {
			usuarioDTO = business.save(usuario);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		assertEquals("jmedina",usuarioDTO.getUsername());
	}
}
