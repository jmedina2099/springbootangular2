/**
 * 
 */
package org.springbootangular.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jmedina
 *
 */
@SpringBootApplication
public class Application {

	static Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		
		logger.debug( "STARTING APP.." );
		
        SpringApplication.run(Application.class, args);
    }
}
