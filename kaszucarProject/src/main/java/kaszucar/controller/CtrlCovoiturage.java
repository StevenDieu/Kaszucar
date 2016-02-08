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
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String date = request.getParameter("date");

		Map<String, Object> infoCovoit = new HashMap<String, Object>();
		if (from != null) {
			infoCovoit.put("from", from);
		} else {
			infoCovoit.put("from", "");
		}

		if (to != null) {
			infoCovoit.put("to", to);
		} else {
			infoCovoit.put("to", "");
		}

		if (date != null) {
			infoCovoit.put("date", date);
		} else {
			infoCovoit.put("date", "");
		}

		return new ModelAndView("covoiturage/proposeCovoit", infoCovoit);
	}

	@RequestMapping(value = "/rechercher-un-covoiturage")
	public String searchCovoit(HttpServletRequest request) {
		return "covoiturage/searchCovoit";
	}
}
