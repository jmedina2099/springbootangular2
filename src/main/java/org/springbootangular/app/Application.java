/**
 * 
 */
package org.springbootangular.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Profile;

/**
 * @author jmedina
 *
 */
@Profile(value = {"container","container-prod"})
@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {

		logger.debug("STARTING APP..");
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {

		logger.debug("configure APP..");
		return builder.sources(Application.class);
	}
}
