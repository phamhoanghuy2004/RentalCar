package com.javaweb.entity;

import jakarta.persistence.*;

@Entity
public class OwnerEntity extends UserEntity {
	@ManyToOne
    @JoinColumn(name = "address_owner_id", nullable = false)
    private AddressEntity address_owner_id;

	public AddressEntity getAddress_owner_id() {
		return address_owner_id;
	}

	public void setAddress_owner_id(AddressEntity address_owner_id) {
		this.address_owner_id = address_owner_id;
	}
	

	@ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role_owner;

	public RoleEntity getRole_owner() {
		return role_owner;
	}

	public void setRole_owner(RoleEntity role_owner) {
		this.role_owner = role_owner;
	}
	
	
}
