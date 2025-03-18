package com.javaweb.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class RoleEntity {
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 	

    @Column(nullable = false)
 	private String name;
 	

    @Column(nullable = false)
 	private String code;
 	
 	@OneToMany(mappedBy = "role_staff", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<StaffEntity> staffEntities;
 	
 	@OneToMany(mappedBy = "role_owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<OwnerEntity> OwnerEntities;
 	
 	@OneToMany(mappedBy = "role_customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<CustomerEntity> customerEntities;
 	
	
	
	

	public List<StaffEntity> getStaffEntities() {
		return staffEntities;
	}



	public void setStaffEntities(List<StaffEntity> staffEntities) {
		this.staffEntities = staffEntities;
	}



	public List<OwnerEntity> getOwnerEntities() {
		return OwnerEntities;
	}



	public void setOwnerEntities(List<OwnerEntity> ownerEntities) {
		OwnerEntities = ownerEntities;
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



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
 	
}
