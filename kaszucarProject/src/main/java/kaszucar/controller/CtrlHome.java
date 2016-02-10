package kaszucar.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CtrlHome {

	@RequestMapping(value = "/")
	public ModelAndView index(HttpServletRequest request) {
		Map<String, Object> infoCovoit = new HashMap<String, Object>();
		return new ModelAndView("index", infoCovoit);
	}
}
