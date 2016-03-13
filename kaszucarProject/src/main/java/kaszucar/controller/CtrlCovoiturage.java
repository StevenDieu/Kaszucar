package kaszucar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kaszucar.model.Users;
import kaszucar.service.ImplCovoiturageService;

@Controller
public class CtrlCovoiturage {

	@Autowired
	private ImplCovoiturageService ICS;

	@RequestMapping(value = "/proposer-un-covoiturage")
	public ModelAndView proposeCovoit(HttpServletRequest request) {
		Users user = (Users) request.getSession().getAttribute("User");
		if (user == null) {
			Map<String, Object> infoRedirect = new HashMap<String, Object>();
			infoRedirect.put("redirect", "proposer-un-covoiturage");
			return new ModelAndView("redirect:connexion", infoRedirect);
		}

		Map<String, Object> infoCovoit = new HashMap<String, Object>();
		infoCovoit.put("from", request.getParameter("from"));
		infoCovoit.put("to", request.getParameter("to"));
		infoCovoit.put("date", request.getParameter("date"));
		infoCovoit.put("cars", ICS.getAllCarsByUser(user.getIdUsers()));

		return new ModelAndView("covoiturage/proposeCovoit", infoCovoit);
	}

	@RequestMapping(value = "/rechercher-un-covoiturage")
	public String searchCovoit(HttpServletRequest request) {
		return "covoiturage/searchCovoit";
	}

	@RequestMapping(value = "/ajouter-un-covoiturage")
	public ModelAndView addCovoit(HttpServletRequest request) {
		String cityFrom = (String) request.getParameter("cityFrom");//*
		String cityTo = (String) request.getParameter("cityTo");//*
		String waypoints = (String) request.getParameter("waypoints");
		String dateFirstTrip = (String) request.getParameter("dateFirstTrip");//*
		String goReturn = (String) request.getParameter("goReturn");
		String dateReturnTrip = (String) request.getParameter("dateReturnTrip");
		String price = (String) request.getParameter("price");//*
		String description = (String) request.getParameter("description");
		String sizeOfLuggage = (String) request.getParameter("sizeOfLuggage");//*
		String smoking = (String) request.getParameter("smoking");
		String annimals = (String) request.getParameter("annimals");
		String muscis = (String) request.getParameter("muscis");
		String detour = (String) request.getParameter("detour");
		String food = (String) request.getParameter("food");
		String sitNumber = (String) request.getParameter("sitNumber");//*
		String chooseCar = (String) request.getParameter("chooseCar");
		String brand = (String) request.getParameter("brand");//*
		String model = (String) request.getParameter("model");//*
		String comfort = (String) request.getParameter("comfort");//*
		String color = (String) request.getParameter("color");//*
		
		

		
		return new ModelAndView("covoiturage/finalizationAddCovoit");
	}
}
