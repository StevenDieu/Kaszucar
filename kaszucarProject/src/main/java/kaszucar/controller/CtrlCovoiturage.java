package kaszucar.controller;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kaszucar.model.Cars;
import kaszucar.model.Covoiturage;
import kaszucar.model.Preference;
import kaszucar.model.Users;
import kaszucar.service.ImplCovoiturageService;
import kaszucar.util.Util;

@Controller
public class CtrlCovoiturage {

  @Autowired
  private ImplCovoiturageService ICS;

  @RequestMapping(value = "/proposer-un-covoiturage", method = RequestMethod.POST)
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

  @RequestMapping(value = "/rechercher-un-covoiturage/{from}/{to}")
  public ModelAndView searchCovoit(HttpServletRequest request,
      @PathVariable("from") Optional<String> fromUrl, @PathVariable("to") Optional<String> toUrl) {
    Map<String, Object> infoCovoit = new HashMap<String, Object>();

    String from = Util.getParametersString(fromUrl, null);
    String to = Util.getParametersString(fromUrl, null);
    
    if(Util.stringIsNull(from)||Util.stringIsNull(to) ){
      
    }

    infoCovoit.put("from", request.getParameter("from"));
    infoCovoit.put("to", request.getParameter("to"));
    infoCovoit.put("date", request.getParameter("date"));

    return new ModelAndView("covoiturage/searchCovoit", infoCovoit);
  }

  @RequestMapping(value = "/ajouter-un-covoiturage", method = RequestMethod.POST)
  public ModelAndView addCovoit(HttpServletRequest request) throws UnsupportedEncodingException {
    Users user = (Users) request.getSession().getAttribute("User");

    if (user == null) {
      Map<String, Object> infoRedirect = new HashMap<String, Object>();
      infoRedirect.put("redirect", "proposer-un-covoiturage");
      return new ModelAndView("redirect:connexion", infoRedirect);
    }
    request.setCharacterEncoding("UTF-8");

    String cityFrom = (String) request.getParameter("cityFrom");
    String cityTo = (String) request.getParameter("cityTo");
    String[] waypoints = request.getParameterValues("waypoints[]");
    String dateFirstTripString = (String) request.getParameter("dateFirstTrip");
    String hoursFirstTrip = (String) request.getParameter("hoursFirstTrip");
    String minFirstTrip = (String) request.getParameter("minFirstTrip");
    String goReturn = (String) request.getParameter("goReturn");
    String dateReturnTripString = (String) request.getParameter("dateReturnTrip");
    String hoursReturnTrip = (String) request.getParameter("hoursReturnTrip");
    String minReturnTrip = (String) request.getParameter("minReturnTrip");

    String priceString = (String) request.getParameter("price");
    String description = (String) request.getParameter("description");
    String sizeOfLuggage = (String) request.getParameter("sizeOfLuggage");
    String smoking = (String) request.getParameter("smoking");
    String animals = (String) request.getParameter("animals");
    String musics = (String) request.getParameter("musics");
    String detour = (String) request.getParameter("detour");
    String food = (String) request.getParameter("food");
    String sitNumberString = (String) request.getParameter("sitNumber");
    String chooseCarString = (String) request.getParameter("chooseCar");
    String brand = (String) request.getParameter("brand");
    String model = (String) request.getParameter("model");
    String comfort = (String) request.getParameter("comfort");
    String color = (String) request.getParameter("color");

    if (Util.stringIsNull(cityFrom) || Util.stringIsNull(cityTo)
        || Util.stringIsNull(dateFirstTripString) || Util.stringIsNull(hoursFirstTrip)
        || Util.stringIsNull(priceString) || Util.stringIsNull(sizeOfLuggage)
        || Util.stringIsNull(sitNumberString)) {
      return Util.returnMessageError("Un problème est survenu, il manque des champs.");
    }

    Date dateFirstTrip = Util.getDateByParam(dateFirstTripString, hoursFirstTrip, minFirstTrip);
    if (dateFirstTrip == null) {
      return Util.returnMessageError("Le format de la date est incorrect.");
    }
    Date dateReturnTrip = null;
    if (Util.stringIsNotNull(goReturn)) {
      dateReturnTrip = Util.getDateByParam(dateReturnTripString, hoursReturnTrip, minReturnTrip);
      if (dateReturnTrip == null) {
        return Util.returnMessageError("Le format de la date est incorrect.");
      }
      if (dateReturnTrip.getTime() <= dateFirstTrip.getTime()) {
        return Util.returnMessageError("La date de retour doit être supérieur à la date d'aller.");
      }
    }

    if (!Util.isPrice(priceString) || !Util.convertToInt(priceString)) {
      return Util.returnMessageError("Le prix est incorrect");

    }
    int price = Integer.parseInt(priceString);

    if (!Util.convertToInt(sitNumberString)) {
      return Util.returnMessageError("Le nombre de place est incorrect.");
    }

    int sitNumber = Integer.parseInt(sitNumberString);
    if (sitNumber < 1 && sitNumber > 5) {
      return Util.returnMessageError("Le nombre de place est incorrect.");
    }
    Cars cars;
    if (chooseCarString.equals("1")) {
      if (!Util.convertToInt(chooseCarString)) {
        return Util.returnMessageError("Une erreur est survenue sur le choix de votre voiture.");
      }
      int idCars = Integer.parseInt(chooseCarString);
      cars = ICS.getCarsByIdAndUser(idCars, user.getIdUsers());
      if (cars == null) {
        return Util.returnMessageError("Une erreur est survenue sur le choix de votre voiture.");
      }
    } else {
      if (Util.stringIsNull(brand) || Util.stringIsNull(model) || Util.stringIsNull(comfort)
          || Util.stringIsNull(color)) {
        return Util.returnMessageError("Une erreur est survenue sur le choix de votre voiture.");
      }
      cars = new Cars();
      cars.setColor(color);
      cars.setComfort(comfort);
      cars.setModel(model);
      cars.setBrand(brand);
      ICS.insertCars(cars, user);
    }

    Preference preference = new Preference();
    preference.setAnimals(Util.stringIsNotNull(animals));
    preference.setDetour(Util.stringIsNotNull(detour));
    preference.setFood(Util.stringIsNotNull(food));
    preference.setMusics(Util.stringIsNotNull(musics));
    preference.setSmoking(Util.stringIsNotNull(smoking));
    ICS.getIdPreference(preference);

    Covoiturage covoiturage = new Covoiturage();
    covoiturage.setCars(cars);
    covoiturage.setCityFrom(cityFrom);
    covoiturage.setCityTo(cityTo);
    covoiturage.setDateFirstTrip(dateFirstTrip);
    covoiturage.setDateReturnTrip(dateReturnTrip);
    covoiturage.setDescription(Util.ConvertStringToNull(description));
    covoiturage.setPreference(preference);
    covoiturage.setPrice(price);
    covoiturage.setSitNumber(sitNumber);
    covoiturage.setSizeOfLuggage(sizeOfLuggage);
    ICS.insertCovoiturage(covoiturage, user);

    if(waypoints!= null){
        ICS.insertWaypoints(waypoints, covoiturage);
    }

    return new ModelAndView("covoiturage/finalizationAddCovoit");
  }

}
