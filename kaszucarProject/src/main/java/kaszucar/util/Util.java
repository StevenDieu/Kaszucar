package kaszucar.util;

public final class Util {

	public static boolean convertToInt(String text) {

		try {
			Integer.parseInt(text);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
