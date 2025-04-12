package com.javaweb.entity;

import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "carline")
public class CarLineEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    @Column(name = "code" , nullable = false)
	private String code;
	

    @Column(name = "name", nullable = false)
	private String name;
	

    @Column(name = "status" , nullable = false)
	private String status;
	
	@OneToMany(mappedBy = "line", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<CarEntity> cars;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<CarEntity> getCars() {
		return cars;
	}

	public void setCars(List<CarEntity> cars) {
		this.cars = cars;
	}
	
	 
}
