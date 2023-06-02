/**
 * 
 */
package org.springbootangular.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * @author jmedina
 *
 */
@Profile(value = {"dev","prod"})
@SpringBootApplication
public class ApplicationEmbedded {

	static Logger logger = LoggerFactory.getLogger(ApplicationEmbedded.class);
	
	public static void main(String[] args) {
		
		logger.debug( "STARTING APP.." );
        SpringApplication.run(ApplicationEmbedded.class, args);
    }
}
