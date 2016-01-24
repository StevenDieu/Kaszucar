package controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class authentication {

	@RequestMapping(value = "/inscription")
	public String signUp(HttpServletRequest request) {
		return "authentication/signUp";
	}

	@RequestMapping(value = "/connexion")
	public String signIn(HttpServletRequest request) {
		return "authentication/signIn";
	}
}
