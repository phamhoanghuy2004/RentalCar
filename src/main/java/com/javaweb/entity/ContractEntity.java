package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class ContractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

    @Column(nullable = false)
	private String status;
	

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    
    
    @OneToOne
    @JoinColumn(name = "payment_id") // Phương thức thanh toán
    private PaymentEntity payment_contract;
    
    @ManyToMany(mappedBy = "contractEntities")
    private List<CarEntity> carEntities;

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}


	

	public PaymentEntity getPayment_contract() {
		return payment_contract;
	}

	public void setPayment_contract(PaymentEntity payment_contract) {
		this.payment_contract = payment_contract;
	}

	public List<CarEntity> getCarEntities() {
		return carEntities;
	}

	public void setCarEntities(List<CarEntity> carEntities) {
		this.carEntities = carEntities;
	}

	
	
}
