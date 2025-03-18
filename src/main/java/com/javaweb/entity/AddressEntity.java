package com.javaweb.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;





@Entity
public class AddressEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	

	@Column(nullable = false)
	private String street;
	
	
	@Column(nullable = false)
	private String district;
	
	
	@Column(nullable = false)
	private String city;
	
	
	
	@OneToMany(mappedBy = "address_staff_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<StaffEntity> staffEntities;
	
	@OneToMany(mappedBy = "address_owner_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<OwnerEntity> ownerEntities;
	
	@OneToMany(mappedBy = "address_customer_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CustomerEntity> customerEntities;
	
	@OneToMany(mappedBy = "address_car_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarEntity> carEntities;
	
	
	
	

	public List<StaffEntity> getStaffEntities() {
		return staffEntities;
	}

	public void setStaffEntities(List<StaffEntity> staffEntities) {
		this.staffEntities = staffEntities;
	}

	public List<OwnerEntity> getOwnerEntities() {
		return ownerEntities;
	}

	public void setOwnerEntities(List<OwnerEntity> ownerEntities) {
		this.ownerEntities = ownerEntities;
	}

	public List<CustomerEntity> getCustomerEntities() {
		return customerEntities;
	}

	public void setCustomerEntities(List<CustomerEntity> customerEntities) {
		this.customerEntities = customerEntities;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<CarEntity> getCarEntities() {
		return carEntities;
	}

	public void setCarEntities(List<CarEntity> carEntities) {
		this.carEntities = carEntities;
	}


	
	

}
