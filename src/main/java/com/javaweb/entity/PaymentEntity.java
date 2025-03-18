package com.javaweb.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class PaymentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
    @Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;

    @Column(nullable = false)
	private Integer price;
    
    
    @OneToOne(mappedBy = "payment_contract")
    private ContractEntity contractEntity;
    
    @OneToOne(mappedBy = "payment_maintenance")
    private MaintenanceBillEntity maintenanceBilltEntity;

	
    
	public MaintenanceBillEntity getMaintenanceBilltEntity() {
		return maintenanceBilltEntity;
	}

	public void setMaintenanceBilltEntity(MaintenanceBillEntity maintenanceBilltEntity) {
		this.maintenanceBilltEntity = maintenanceBilltEntity;
	}

	public ContractEntity getContractEntity() {
		return contractEntity;
	}

	public void setContractEntity(ContractEntity contractEntity) {
		this.contractEntity = contractEntity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	
	
}
