package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "contracts")
public class ContractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    @Column(name = "status", nullable = false)
	private String status;
	

    @Column(name = "datefrom", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;
    

    @Column(name = "dateto" , nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;
    
    @Column(name = "price", nullable = false)
    private Long price;
    
    @OneToOne
    @JoinColumn(name = "paymentid")          // Phương thức thanh toán
    private PaymentEntity payment_of_contract;
    
    @ManyToMany(mappedBy = "contracts", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CarEntity> cars;
    
    @ManyToOne
    @JoinColumn(name = "customerid")
    private CustomerEntity  customer;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public PaymentEntity getPayment_of_contract() {
		return payment_of_contract;
	}

	public void setPayment_of_contract(PaymentEntity payment_of_contract) {
		this.payment_of_contract = payment_of_contract;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
    
    
	
}
