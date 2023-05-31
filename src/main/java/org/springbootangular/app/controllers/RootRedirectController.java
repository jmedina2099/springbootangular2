/**
 * 
 */
package org.springbootangular.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jmedina
 *
 */
@Controller
public class RootRedirectController {
	
	@Value("${context.path}")
	private String contextPath;

	@GetMapping(value = {"/", "/${context.path}", "/${context.path}/" })
    public ModelAndView redirectToServices(){
		return new ModelAndView("redirect:/"+ this.contextPath +"/index.html");    }

}
