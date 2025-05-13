package com.javaweb.beans.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class VerifyOTPRequest {

	@Email(message = "Không thể tìm thấy tài khoản vui lòng thử lại!")
	@NotEmpty(message = "Không thể tìm thấy tài khoản vui lòng thử lại!")
	private String email;
	
	@NotEmpty(message = "OTP không được trống")
	private String otp;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
}
