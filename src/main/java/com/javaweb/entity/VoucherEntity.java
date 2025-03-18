package com.javaweb.entity;

import java.util.Date;



import jakarta.persistence.*;


@Entity
public class VoucherEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	

    @Column(nullable = false)
	private int value;
	
	private String description;
	

    @Column(nullable = false)
	private int voucher_condition;
	
	
    @Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateFrom;
	
	
    @Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateTo;

    
    
	public int getVoucher_condition() {
		return voucher_condition;
	}

	public void setVoucher_condition(int voucher_condition) {
		this.voucher_condition = voucher_condition;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
	
	
	    

}
