package com.javaweb.entity;

import java.util.List;


import jakarta.persistence.*;


@Entity
public class CarBrandEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

    @Column(nullable = false)
	private String code;
	

    @Column(nullable = false)
	private String name;
	
	@Lob
    @Column(columnDefinition = "LONGBLOB") 
	private byte[] logo; 
	

    @Column(nullable = false)
	private String description;
	

    @Column(nullable = false)
	private int status;
	
	@ManyToMany
    @JoinTable(
        name = "Line_Brand",
        joinColumns = @JoinColumn(name = "brand_id"),
        inverseJoinColumns = @JoinColumn(name = "line_id")
    )
    private List<CarLineEntity> carLineEntities; 
	
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CarEntity> carEntities;

	
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

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<CarLineEntity> getCarLineEntities() {
		return carLineEntities;
	}

	public void setCarLineEntities(List<CarLineEntity> carLineEntities) {
		this.carLineEntities = carLineEntities;
	}

	public List<CarEntity> getCarEntities() {
		return carEntities;
	}

	public void setCarEntities(List<CarEntity> carEntities) {
		this.carEntities = carEntities;
	}
	
	
	
	
	    
}
