package com.javaweb.entity;

import jakarta.persistence.*;

@Entity
public class CustomerEntity extends UserEntity {
	@ManyToOne
    @JoinColumn(name = "address_customer_id", nullable = false)
    private AddressEntity address_customer_id;

	public AddressEntity getAddress_customer_id() {
		return address_customer_id;
	}

	public void setAddress_customer_id(AddressEntity address_customer_id) {
		this.address_customer_id = address_customer_id;
	}
	
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role_customer;

	public RoleEntity getRole_customer() {
		return role_customer;
	}

	public void setRole_customer(RoleEntity role_customer) {
		this.role_customer = role_customer;
	}
	
	
	
}
