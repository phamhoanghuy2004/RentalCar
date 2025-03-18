package com.javaweb.entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
public class CarLineEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

    @Column(nullable = false)
	private String code;
	

    @Column(nullable = false)
	private String name;
	

    @Column(nullable = false)
	private int status;
	
	@ManyToMany(mappedBy = "carLineEntities")
    private List<CarBrandEntity> carBrandEnties;
	
	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public List<CarBrandEntity> getCarBrandEnties() {
		return carBrandEnties;
	}
	public void setCarBrandEnties(List<CarBrandEntity> carBrandEnties) {
		this.carBrandEnties = carBrandEnties;
	}
	
	 
}
