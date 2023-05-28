/**
 * 
 */
package org.springbootangular.app.dto;

import org.springbootangular.app.entities.Usuario;

/**
 * @author jmedina
 *
 */
public class UsuarioDTO {

    private Long id;
    private String username;
    private String password;    
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    
	public UsuarioDTO() {
	}

	public UsuarioDTO( Usuario usuario ) {
		this.id = usuario.getId();
		this.username = usuario.getUsername();
		this.firstName = usuario.getFirstName();
		this.middleName = usuario.getMiddleName();
		this.lastName = usuario.getLastName();
		this.email = usuario.getEmail();
	}
	
	public Usuario transform() {
		Usuario usuario = new Usuario();
		usuario.setId( getId() );
		usuario.setUsername( getUsername() );
		usuario.setPassword( getPassword() );
		usuario.setFirstName( getFirstName() );
		usuario.setMiddleName( getMiddleName() );
		usuario.setLastName( getLastName() );
		usuario.setEmail( getEmail() );
		return usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
