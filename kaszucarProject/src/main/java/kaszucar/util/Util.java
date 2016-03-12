package kaszucar.util;

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

	public static void checkIfConnectedAndAddToParmaters(HttpServletRequest request, Map<String, Object> listParameters) {
		if (request.getSession().getAttribute("User") != null) {
			listParameters.put("users", request.getSession().getAttribute("User"));
		}
	}

}
