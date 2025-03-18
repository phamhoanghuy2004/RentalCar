package com.javaweb.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
public class MaintenanceBillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

    @Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	 @ManyToOne
	 @JoinColumn(name = "staff_id") // Nhân viên thực hiện bảo trì
	 private StaffEntity staff_bao_tri;
	
	 @ManyToOne
	 @JoinColumn(name = "car_id") // Xe được bảo trì
	 private CarEntity car_maintenance;
	
	 @OneToOne
	 @JoinColumn(name = "payment_id") // Phương thức thanh toán
	 private PaymentEntity payment_maintenance;

	

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

	

	public StaffEntity getStaff_bao_tri() {
		return staff_bao_tri;
	}

	public void setStaff_bao_tri(StaffEntity staff_bao_tri) {
		this.staff_bao_tri = staff_bao_tri;
	}

	

	public PaymentEntity getPayment_maintenance() {
		return payment_maintenance;
	}

	public void setCar_maintenance(CarEntity car_maintenance) {
		this.car_maintenance = car_maintenance;
	}

	public CarEntity getCar_maintenance() {
		return car_maintenance;
	}

	

	public void setPayment_maintenance(PaymentEntity payment_maintenance) {
		this.payment_maintenance = payment_maintenance;
	}

	
	
	 
	
	
}
