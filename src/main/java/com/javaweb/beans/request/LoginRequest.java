package com.javaweb.beans.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	
	@NotEmpty(message = "Email không được trống")
	@Email(message = "Nhập đúng định dạng email")
	private String email;
	
	@NotEmpty(message = "Mật khẩu không được trống")
	@Size(min = 5, message = "Mật khẩu phải lớn hơn hoặc bằng 5 ký tự")
	private String password;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
