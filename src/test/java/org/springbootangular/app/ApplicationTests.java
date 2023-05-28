/**
 * 
 */
package org.springbootangular.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springbootangular.app.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jmedina
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ApplicationTests {
	
	private Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
	
	@LocalServerPort
    private String port;
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
    public void getUsers() throws Exception {
		
		String url = "http://localhost:" + port + "/angularapp/users";

		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		
		ResponseEntity<Object[]> response = restTemplate.getForEntity(
			new URL(url).toString(), Object[].class);
        
		Object[] objects = response.getBody();
        
        logger.debug( "0-body= "+objects );
        
        ObjectMapper mapper = new ObjectMapper();
        
        lista = Arrays.stream(objects)
        	.map(object -> mapper.convertValue(object, UsuarioDTO.class))
        	.collect(Collectors.toList());
        
        assertEquals(2,lista.size());
        
        UsuarioDTO user1 = lista.get(0);
        assertEquals(1,user1.getId());
        
        assertEquals("admin",user1.getUsername());
        
        UsuarioDTO user2 = lista.get(1);
        assertEquals(2,user2.getId());
        
        assertEquals("user",user2.getUsername());
    }
	
	@Test
	public void modifyUser() throws Exception {
		
		String url = "http://localhost:" + port + "/angularapp/update";
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId( 2l );
		usuarioDTO.setUsername( "jmedina" );
		usuarioDTO.setFirstName( "George" );
		usuarioDTO.setPassword( "hola123" );
		usuarioDTO.setEmail( "" );
		
		RequestEntity<UsuarioDTO> requestEntity = new RequestEntity<UsuarioDTO>(usuarioDTO,HttpMethod.PUT,new URL(url).toURI()); 
		ResponseEntity<String> response = restTemplate.exchange(requestEntity,String.class);
		assertEquals(409,response.getStatusCode().value());
	}
	
	@Test
	public void removeUser() throws Exception {
		
		String url = "http://localhost:" + port + "/angularapp/delete";
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId( 3l );
		
		RequestEntity<UsuarioDTO> requestEntity = new RequestEntity<UsuarioDTO>(usuarioDTO,HttpMethod.DELETE,new URL(url).toURI()); 
		ResponseEntity<String> response = restTemplate.exchange(requestEntity,String.class);
		assertEquals(400,response.getStatusCode().value());
	}

	@Test
	public void saveUser() throws Exception {
		
		String url = "http://localhost:" + port + "/angularapp/create";
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsername( "jmedina" );
		usuarioDTO.setPassword( "hola123" );
		usuarioDTO.setEmail( "medinarosas.jorgealberto@gmail.com" );
		
		ResponseEntity<Object> response = restTemplate.postForEntity(
				new URL(url).toString(),
				usuarioDTO,
				Object.class);
		
		Object object = response.getBody();
        
        logger.debug( "1-body= "+object );
        
        ObjectMapper mapper = new ObjectMapper();
        
        UsuarioDTO respuesta = mapper.convertValue(object, UsuarioDTO.class);
        assertEquals(3,respuesta.getId());
	}
	
	@Test
	public void testCreate() throws Exception {
		String url = "http://localhost:" + port + "/angularapp/delete";
		
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId( 3l );
		
		RequestEntity<UsuarioDTO> requestEntity = new RequestEntity<UsuarioDTO>(usuarioDTO,HttpMethod.DELETE,new URL(url).toURI()); 
		ResponseEntity<String> response = restTemplate.exchange(requestEntity,String.class);
		assertEquals(200,response.getStatusCode().value());
	}
	
	
}
