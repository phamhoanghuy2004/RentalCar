package com.javaweb.beans.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ResetPasswordRequest {
	@Email(message = "Không thể tìm thấy tài khoản vui lòng thử lại!")
	@NotEmpty(message = "Không thể tìm thấy tài khoản vui lòng thử lại!")
	private String email;
	
	@NotEmpty(message = "Email không được trống")
	@Size(min = 5, message = "Mật khẩu phải lớn hơn 5 ký tự!")
	private String newPassword;
	
	@NotEmpty(message = "Lỗi xác thực, Vui lòng xác thực OTP!")
	private String otp;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	
}
