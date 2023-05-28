/**
 * 
 */
package org.springbootangular.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author jmedina
 *
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;
    
    @NotNull
    @NotEmpty
    @Column(name="username")
    private String username;

    @NotNull
    @NotEmpty
    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_name")
    private String middleName;

    @Column(name="last_name")
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(name="email")
    private String email;
    
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", firstName=" + firstName + ", email=" + email + '}';
    }
	
}
