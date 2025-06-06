package com.javaweb.beans;

import java.util.Date;
import java.util.List;

public class ContractDTO {
	private Long id;
	private String status;
	private Date dateFrom;
	private Date dateTo;
	private Long price;
	private CustomerDTO customerDTO;
	private List<CarDTO> cars;
	private PaymentDTO paymentDTO;
	
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
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	public List<CarDTO> getCars() {
		return cars;
	}
	public void setCars(List<CarDTO> cars) {
		this.cars = cars;
	}
	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}
	public void setPaymentDTO(PaymentDTO paymentDTO) {
		this.paymentDTO = paymentDTO;
	}
	
	
}
