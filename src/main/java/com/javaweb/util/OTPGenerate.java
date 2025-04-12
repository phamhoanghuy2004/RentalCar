package com.javaweb.util;

import java.util.Random;

public class OTPGenerate {
	public static String generateOTP () {
		Random random = new Random();	
		String otp = String.valueOf(random.nextInt(900000) + 100000);
		return otp;
	}
}
