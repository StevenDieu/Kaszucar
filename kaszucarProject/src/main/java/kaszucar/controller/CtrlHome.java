package kaszucar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CtrlHome {
	
	@RequestMapping(value = "/")
	public ModelAndView index(HttpServletRequest request) {

		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");

		return modelAndView;
	}

	@RequestMapping(value = "/getCity", method = RequestMethod.POST)
	@ResponseBody
	public String getCity(HttpServletRequest request) {
		String city = request.getParameter("city");

		return city;
	}

}
