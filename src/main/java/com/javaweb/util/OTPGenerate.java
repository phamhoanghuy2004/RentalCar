package com.javaweb.util;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class OTPGenerate {
	
	 @Autowired
	 private StringRedisTemplate redisTemplate;
	 
	 private final long EXPIRE_MINUTES = 5;
	 
	public String generateOTP (String email) {
		Random random = new Random();	
		String otp = String.valueOf(random.nextInt(900000) + 100000);
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
	    ops.set("OTP_" + email, otp, Duration.ofMinutes(EXPIRE_MINUTES));
		return otp;
	}
	
	public static String generateOTP2 () {
		Random random = new Random();	
		String otp = String.valueOf(random.nextInt(900000) + 100000);
		return otp;
	}
	
	 public boolean verifyOTP(String email, String inputOtp, String action) {
	        String key = "OTP_" + email;
	        String savedOtp = redisTemplate.opsForValue().get(key);
	        if (savedOtp != null && savedOtp.equals(inputOtp)) {
	        	if (action.equals("reset")) {
	        		redisTemplate.delete(key); // OTP dùng 1 lần
	        	}
	            return true;
	        }
	        return false;
	    }
}
