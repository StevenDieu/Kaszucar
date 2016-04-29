package kaszucar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kaszucar.util.Util;

@Controller
public class CtrlHome {

    /**
     * Controller qui permet d'afficher la home page
     * 
     * @param request
     * @return
     */
	@RequestMapping(value = "/")
	public ModelAndView index(HttpServletRequest request) {
	    return Util.ModelAndView("index",request);
	}
}
