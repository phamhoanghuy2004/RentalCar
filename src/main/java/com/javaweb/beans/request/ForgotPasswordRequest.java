package com.javaweb.beans.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class ForgotPasswordRequest {
	@NotEmpty(message = "Email không được trống")
	@Email(message = "Nhập đúng định dạng email")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
