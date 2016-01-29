package kaszucar.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kaszucar.service.UserService;
import kaszucar.util.Util;

@Controller
public class CtrlUser {

	@Autowired
	private UserService US;

	@RequestMapping(value = "/inscription")
	public String signUp(HttpServletRequest request) {
		return "authentication/signUp";
	}

	@RequestMapping(value = "/connexion")
	public String signIn(HttpServletRequest request) {
		return "authentication/signIn";
	}

	@RequestMapping(value = "/ajaxConnexion", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxSignIn(HttpServletRequest request) {

		String email = request.getParameter("email");
		String password = request.getParameter("pwd");

		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "{\"statut\": \"ok\"}";
		} else if (!US.isEmailAdress(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"L'adresse email n'est pas valide.\"}";
		} else if (email == "" || password == "") {
			return "{\"statut\": \"nok\",\"message\":  \"Tout les champs sont obligatoires.\"}";
		} else if (!US.checkEmail(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"Cette adresse email n'existe pas.\"}";
		} else if (!US.connexion(email, password, request)) {
			return "{\"statut\": \"nok\",\"message\":  \"Le mot de passe est incorrect.\"}";
		}

		return "{\"statut\": \"ok\"}";

	}

	@RequestMapping(value = "/ajaxInscription", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxSignUp(HttpServletRequest request) {

		String gender = request.getParameter("gender");
		String name = request.getParameter("name");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String sYearBirth = request.getParameter("yearBirth");
		String cgv = request.getParameter("cgv");

		if (!Util.convertToInt(sYearBirth)) {
			return "{\"statut\": \"nok\",\"message\":  \"L'année doit être un chiffre.\"}";
		}

		int yearBirth = Integer.parseInt(sYearBirth);

		if (request.getSession().getAttribute("boolConnexion") != null) {
			return "{\"statut\": \"ok\"}";
		} else if (password.length() < 6 || password.length() > 54) {
			return "{\"statut\": \"nok\",\"message\":  \"Votre mot de passe doit contenir entre 6 et 54 charactères.\"}";
		} else if (US.checkYear18(yearBirth)) {
			return "{\"statut\": \"nok\",\"message\":  \"Vous devez avoir au moins de 18 ans.\"}";
		} else if (gender == "" || name == "" || lastName == "" || email == "" || password == "" || sYearBirth == "" || cgv == "") {
			return "{\"statut\": \"nok\",\"message\":  \"Tout les champs sont obligatoires.\"}";
		} else if (!US.isEmailAdress(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"L'adresse email n'est pas valide.\"}";
		} else if (US.checkEmail(email)) {
			return "{\"statut\": \"nok\",\"message\":  \"Cette adresse email est déja utilisé.\"}";
		}

		US.register(gender, name, lastName, email, password, yearBirth, request);
		return "{\"statut\": \"ok\"}";

	}
}
