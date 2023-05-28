/**
 * 
 */
package org.springbootangular.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jmedina
 *
 */
@Controller
public class RootRedirectController {

	@GetMapping(value = {"/", "/angularapp", "/angularapp/" })
    public ModelAndView redirectToServices(){
		return new ModelAndView("redirect:/angularapp/index.html");    }

}
