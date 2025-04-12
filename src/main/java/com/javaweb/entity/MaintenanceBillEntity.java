package com.javaweb.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenancebill")
public class MaintenanceBillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    @Column(name = "date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
    
    @Column(name = "price", nullable = false)
    private Long price;
	
	@ManyToOne
	@JoinColumn(name = "staffid") // Nhân viên thực hiện bảo trì
	private UserEntity staff_of_bill;
	
	@ManyToOne
	@JoinColumn(name = "carid") // Xe được bảo trì
	private CarEntity car;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "paymentid") // Phương thức thanh toán
	private PaymentEntity payment_maintenance;

	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}


	public UserEntity getStaff_of_bill() {
		return staff_of_bill;
	}

	public void setStaff_of_bill(UserEntity staff_of_bill) {
		this.staff_of_bill = staff_of_bill;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentEntity getPayment_maintenance() {
		return payment_maintenance;
	}

	public void setPayment_maintenance(PaymentEntity payment_maintenance) {
		this.payment_maintenance = payment_maintenance;
	}

	

	
	
}
