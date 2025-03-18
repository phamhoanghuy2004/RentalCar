package com.javaweb.entity;

import java.util.Date;


import jakarta.persistence.*;


@MappedSuperclass
// Không ánh xạ thành bảng, chỉ dùng làm lớp cha
public abstract class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String email;


    @Column(nullable = false)
    private String password;
    
 
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
   
    @Column(nullable = false)
    private String sex;
    
    
    @Lob
    @Column(columnDefinition = "LONGBLOB") 
    private byte[] avatar; 
   
    
    

	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




}