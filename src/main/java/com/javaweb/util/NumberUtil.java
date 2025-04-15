package com.javaweb.util;


public class NumberUtil {
	public static boolean  isLong (String input) {
		if (input == null || input.trim().isEmpty()) return false;
		try {
			Long.parseLong(input.trim());
			return true;
		}
		catch (NumberFormatException ex ) {
			return false;
		}
	}
	
	public static boolean  isInt (String input) {
		if (input == null || input.trim().isEmpty()) return false;
		try {
			Integer.parseInt(input.trim());
			return true;
		}
		catch (NumberFormatException ex ) {
			return false;
		}
	}
	
	public static boolean  isFloat (String input) {
		if (input == null || input.trim().isEmpty()) return false;
		try {
			Float.parseFloat(input.trim());
			return true;
		}
		catch (NumberFormatException ex ) {
			return false;
		}
	}
}
