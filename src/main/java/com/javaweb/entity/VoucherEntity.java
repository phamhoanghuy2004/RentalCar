package com.javaweb.entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "voucher" )
public class VoucherEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @Column(name = "value" , nullable = false)
	private Long value;
	
    @Column(name = "description" , nullable = false)
	private String description;
    
    @Column(name = "status" , nullable = false)
	private String status;
	
	@ManyToMany(mappedBy = "vouchers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CarEntity> cars;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}
    
	

}
