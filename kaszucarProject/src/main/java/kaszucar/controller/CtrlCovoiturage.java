package kaszucar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CtrlCovoiturage {
	
	@RequestMapping(value = "/proposer-un-covoiturage")
	public String proposeCovoit(HttpServletRequest request) {
		
		
		return "covoiturage/proposeCovoit";
	}

	
	@RequestMapping(value = "/rechercher-un-covoiturage")
	public String searchCovoit(HttpServletRequest request) {
		return "covoiturage/searchCovoit";
	}
}
