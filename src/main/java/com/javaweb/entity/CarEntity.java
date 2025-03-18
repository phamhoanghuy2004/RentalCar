package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
public class CarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

    @Column(nullable = false)
	private String name;
	

    @Column(nullable = false)
	private String description;
	

    @Column(nullable = false)
	private String status;
	
	@Lob
    @Column(columnDefinition = "LONGBLOB") 
	private byte[] picture; 
	

    @Column(nullable = false)
	private String indentify;
	
	@ManyToOne
    @JoinColumn(name = "address_car_id",  unique = false)
    private AddressEntity address_car_id;
	

    @Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dateOfStart;
	 
	@ManyToOne
	@JoinColumn(name = "brand_id",  unique = false)
    private CarBrandEntity brand;
	
	@ManyToOne
	@JoinColumn(name = "staff_id", unique = false)
	private StaffEntity staff_quan_li;
	
	private int price;
	
	@ManyToMany
	@JoinTable(
	    name = "Car_Contract",
	    joinColumns = @JoinColumn(name = "car_id"),
	    inverseJoinColumns = @JoinColumn(name = "contract_id")
	)
	
	private List<ContractEntity> contractEntities;
	
	@OneToMany(mappedBy = "car_maintenance")
	private List<MaintenanceBillEntity> maintenanceBillEntity;
	
	
	
	public List<MaintenanceBillEntity> getMaintenanceBillEntity() {
		return maintenanceBillEntity;
	}

	public void setMaintenanceBillEntity(List<MaintenanceBillEntity> maintenanceBillEntity) {
		this.maintenanceBillEntity = maintenanceBillEntity;
	}

	public List<ContractEntity> getContractEntities() {
		return contractEntities;
	}

	public void setContractEntities(List<ContractEntity> contractEntities) {
		this.contractEntities = contractEntities;
	}


	public StaffEntity getStaff_quan_li() {
		return staff_quan_li;
	}

	public void setStaff_quan_li(StaffEntity staff_quan_li) {
		this.staff_quan_li = staff_quan_li;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getIndentify() {
		return indentify;
	}

	public void setIndentify(String indentify) {
		this.indentify = indentify;
	}

	

	public AddressEntity getAddress_car_id() {
		return address_car_id;
	}

	public void setAddress_car_id(AddressEntity address_car_id) {
		this.address_car_id = address_car_id;
	}

	public Date getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(Date dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public CarBrandEntity getBrand() {
		return brand;
	}

	public void setBrand(CarBrandEntity brand) {
		this.brand = brand;
	}
	 
	
	
}
