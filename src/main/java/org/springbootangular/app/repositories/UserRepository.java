/**
 * 
 */
package org.springbootangular.app.repositories;

import org.springbootangular.app.entities.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jmedina
 *
 */
public interface UserRepository extends CrudRepository<Usuario, Long>{

	@Transactional
	@Modifying
	@Query("update Usuario u set u.firstName = :#{#usuario.firstName}, "+
			"u.middleName = :#{#usuario.middleName}, u.lastName = :#{#usuario.lastName}, "+
			"u.email = :#{#usuario.email} where u.id = :#{#usuario.id}")
	public void update(@Param("usuario") Usuario usuario);
	
}
