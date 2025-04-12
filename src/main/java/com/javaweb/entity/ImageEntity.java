package com.javaweb.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "image")
public class ImageEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "data", nullable = false)
	private String data;  
	
	@ManyToOne
	@JoinColumn(name = "carid")
	private CarEntity carOfImg;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public CarEntity getCarOfImg() {
		return carOfImg;
	}

	public void setCarOfImg(CarEntity carOfImg) {
		this.carOfImg = carOfImg;
	}
	

}
