package com.javaweb.beans.request;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

public class updateProfileRequest {
	@NotBlank(message = "Email không được để trống!") // bat ca null ca rong luon
	@Email(message = "Nhập đúng định dạng email")
	private String email;
	
	@Past(message = "Ngày sinh phải trước ngày hiện tại")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "Ngày sinh không được trống")// hoặc pattern phù hợp với format đầu vào
    private Date dateOfBirth;
    
	@NotBlank(message = "Giới tính không được trống!")
    private String sex;
    
	@NotBlank(message = "Hình ảnh không được trống!")
    private String avatar;
    
	@NotBlank(message = "Địa chỉ không được trống!")
    private String street;
    
	@NotBlank(message = "Địa chỉ không được trống!")
    private String district;
    
	@NotBlank(message = "Địa chỉ không được trống!")
    private String ward;
    
	@NotBlank(message = "Địa chỉ không được trống!")
    private String province;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
    
    
}
