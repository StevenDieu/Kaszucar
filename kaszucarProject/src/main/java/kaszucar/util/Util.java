package kaszucar.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import kaszucar.model.Users;

public final class Util {

  /**
   * Test si il est possible de convertir en int
   * 
   * @param text
   * @return
   */
  public static boolean convertToInt(String text) {
    try {
      Integer.parseInt(text);
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Test si il est possible de convertir en short
   * 
   * @param sYearBirth
   * @return
   */
  public static boolean convertToShort(String sYearBirth) {
    try {
      Short.parseShort(sYearBirth);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  /**
   * Test si le string est null ou vide ou "null"
   * 
   * @param string
   * @return
   */
  public static boolean stringIsNull(String string) {
    if (string == null || string.equals("") || string.equals("null")
        || string.equals("undefined")) {
      return true;
    }
    return false;
  }

  /**
   * Test si le string est pas null ou pas vide ou pas "null"
   * 
   * @param string
   * @return
   */
  public static boolean stringIsNotNull(String string) {
    return !stringIsNull(string);
  }

  /**
   * Check if the price is compatible
   * 
   * @param email
   * @return
   */
  public static boolean isPrice(String price) {
    if (price == null) {
      return false;
    }
    Pattern p = Pattern.compile("^[0-9]{1,}(,[0-9]{1,2}|[.][0-9]{1,2}){0,1}$");
    Matcher m = p.matcher(price);
    return m.matches();
  }

  /**
   * Récupère la date sous le format yyyy-MM-dd
   * 
   * @param date
   * @return
   */
  public static Date getDateByParam(String date) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    String stringDate = date.replace("-", "/");
    Date d = new Date();
    try {
      d = sdf.parse(stringDate);
      String t = sdf.format(d);
      if (t.compareTo(stringDate) == 0)
        return d;
    } catch (Exception e) {

    }
    return null;
  }

  /**
   * Récupère la date sous le format yyyy-MM-dd HH:mm
   * 
   * @param date
   * @param hours
   * @param min
   * @return
   */
  public static Date getDateByParam(String date, String hours, String min) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String stringDate = date.replace("-", "/") + " " + hours + ":" + min;
    Date d = new Date();
    try {
      d = sdf.parse(stringDate);
      String t = sdf.format(d);
      if (t.compareTo(stringDate) == 0)
        return d;
    } catch (Exception e) {

    }
    return null;
  }

  /**
   * Test si on est connecter et renvoie un paramètre
   * 
   * @param request
   * @param listParameters
   */
  public static void checkIfConnectedAndAddToParmaters(HttpServletRequest request,
      Map<String, Object> listParameters) {
    if (request.getSession().getAttribute("User") != null) {
      listParameters.put("users", request.getSession().getAttribute("User"));
    }
  }

  /**
   * Retourne un message d'erreur
   * 
   * @param messageString
   * @return
   */
  public static ModelAndView returnMessageError(String messageString) {
    Map<String, Object> message = new HashMap<String, Object>();
    message.put("messageError", messageString);
    return new ModelAndView("redirect:proposer-un-covoiturage", message);
  }

  /**
   * Covertit un string null en valeur null
   * 
   * @param description
   * @return
   */
  public static String ConvertStringToNull(String description) {
    if (description == null || description.equals("") || description.equals("null")) {
      return null;
    }
    return description;
  }

  /**
   * Récupère un paramètre de l'url et test la valeur sinon met une valeur par defaut
   * 
   * @param supportUrl
   * @param stringDefault
   * @return
   */
  public static String getParametersString(Optional<String> supportUrl, String stringDefault) {
    String stringParameters;
    if (supportUrl.isPresent()) {
      stringParameters = supportUrl.get();
    } else {
      stringParameters = stringDefault;
    }
    return stringParameters;
  }

  /**
   * return un Model and view
   * 
   * @param string
   * @param request
   * @return
   */
  public static ModelAndView ModelAndView(String string, HttpServletRequest request) {
    return ModelAndView(string, new HashMap<String, Object>(), request);
  }

  /**
   * Model and view avec un paramètre de connexion
   * 
   * @param string
   * @param data
   * @param request
   * @return
   */
  public static ModelAndView ModelAndView(String string, Map<String, Object> data,
      HttpServletRequest request) {
    Users user = (Users) request.getSession().getAttribute("User");
    if (user != null) {
      data.put("user", user);
    } else {
      data.put("notUser", true);
    }
    return new ModelAndView(string, data);
  }

}
