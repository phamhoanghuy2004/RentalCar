package com.javaweb.entity;

import java.util.Date;


import jakarta.persistence.*;



@MappedSuperclass
public abstract class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;


    @Column(name = "password", nullable = false)
    private String password;
    
 
    @Column(name = "dateofbirth", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    
   
    @Column(name = "sex", nullable = false)
    private String sex;
    
    
    @Column(name = "avatar", nullable = false ) 	
    private String avatar;  // Firebase Storage
    
    @Column(name = "status", nullable = false)
    private Integer status;
    
    @Column(name = "name", nullable = false ) 	
    private String name;  // Firebase Storage


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	

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


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}