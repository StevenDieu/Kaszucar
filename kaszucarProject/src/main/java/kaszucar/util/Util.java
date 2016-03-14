package kaszucar.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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



}
