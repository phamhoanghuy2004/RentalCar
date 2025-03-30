package com.javaweb.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends PersonEntity {
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
	private List<ContractEntity> contracts;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adressid")
	private AddressEntity customerAddress;


	public List<ContractEntity> getContracts() {
		return contracts;
	}

	public void setContracts(List<ContractEntity> contracts) {
		this.contracts = contracts;
	}

	public AddressEntity getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(AddressEntity customerAddress) {
		this.customerAddress = customerAddress;
	}
		
	
	
}
