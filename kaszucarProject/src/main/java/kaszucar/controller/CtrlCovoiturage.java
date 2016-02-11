package kaszucar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CtrlCovoiturage {

	@RequestMapping(value = "/proposer-un-covoiturage")
	public ModelAndView proposeCovoit(HttpServletRequest request) {
		Map<String, Object> infoCovoit = new HashMap<String, Object>();
		infoCovoit.put("from", request.getParameter("from"));
		infoCovoit.put("to", request.getParameter("to"));
		infoCovoit.put("date", request.getParameter("date"));

		if (request.getSession().getAttribute("User") == null) {
			Map<String, Object> infoRedirect = new HashMap<String, Object>();
			infoRedirect.put("redirect", "proposer-un-covoiturage");
			return new ModelAndView("redirect:connexion", infoRedirect);
		}

		return new ModelAndView("covoiturage/proposeCovoit", infoCovoit);
	}

	@RequestMapping(value = "/rechercher-un-covoiturage")
	public String searchCovoit(HttpServletRequest request) {
		return "covoiturage/searchCovoit";
	}

	@RequestMapping(value = "/ajouter-un-covoiturage")
	public ModelAndView addCovoit(HttpServletRequest request) {

		return new ModelAndView("covoiturage/finalizationAddCovoit");

	}
}
