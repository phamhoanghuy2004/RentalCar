package com.javaweb.beans.request;

import java.util.Date;

import com.javaweb.beans.CarDTO;
import com.javaweb.beans.CustomerDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class BookingRequest {
	@NotNull(message = "Ngày đặt không được trống!")
	private Date dateFrom;
	
	@NotNull(message = "Ngày đặt không được trống!")
	private Date dateTo;
	
	@NotNull(message = "Số tiền không được trống!")
	@Positive(message = "Số tiền phải lớn hơn 0!")
	private Long price;
	
	@NotNull(message = "Xe không được trống!")
	private Long carId;

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

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	
	
}
