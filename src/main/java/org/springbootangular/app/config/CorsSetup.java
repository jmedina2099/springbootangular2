/**
 * 
 */
package org.springbootangular.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jmedina
 *
 */
@Profile("dev")
@Configuration
public class CorsSetup implements WebMvcConfigurer {

	@Value("${cross-origin.front.path}")
	private String crossFront1;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        	.allowedOrigins( this.crossFront1 );
    }
}
