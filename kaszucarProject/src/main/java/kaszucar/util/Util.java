package kaszucar.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public final class Util {

  public static boolean convertToInt(String text) {

    try {
      Integer.parseInt(text);
    } catch (Exception e) {
      return false;
    }
    return true;

  }

  public static boolean convertToShort(String sYearBirth) {
    try {
      Short.parseShort(sYearBirth);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

  public static boolean stringIsNull(String string) {
    if (string == "" || string == null || string == "null") {
      return true;
    }
    return false;
  }

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
		Pattern p = Pattern.compile("^[0-9]{1,}(,[0-9]{1,2}|[.][0-9]{1,2}){0,1}$");
		Matcher m = p.matcher(price);
		return m.matches();
	}

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
      return null;
    }
    return null;
  }

  public static void checkIfConnectedAndAddToParmaters(HttpServletRequest request,
      Map<String, Object> listParameters) {
    if (request.getSession().getAttribute("User") != null) {
      listParameters.put("users", request.getSession().getAttribute("User"));
    }
  }

	public static ModelAndView returnMessageError(String messageString) {
		Map<String, Object> message = new HashMap<String, Object>();
		message.put("messageError", messageString);
		return new ModelAndView("redirect:proposer-un-covoiturage", message);
	}


}
