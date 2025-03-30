package com.javaweb.entity;

import java.util.List;


import jakarta.persistence.*;


@Entity
@Table(name = "carbrand")
public class CarBrandEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    @Column(name = "code" , nullable = false)
	private String code;
	

    @Column(name = "name" , nullable = false)
	private String name;
	
    
    @Column(name = "logo" , nullable = false) 
	private String logo; 
	

    @Column(name = "description" , nullable = false)
	private String description;
	

    @Column( name = "status" , nullable = false)
	private String status;
	
	
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarEntity> cars;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<CarEntity> getCars() {
		return cars;
	}


	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}
	
	
	
	
	    
}
