package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name = "car")
public class CarEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    @Column(name = "name", nullable = false)
	private String name;
	

    @Column(name = "description" , nullable = false)
	private String description;
	

    @Column(name = "status" , nullable = false)
	private String status;
	  
    @Column(name = "indentify", nullable = false)
	private String indentify;   // bien so
    
    @Column(name = "dateofstart", nullable = false)
   	@Temporal(TemporalType.DATE)
   	private Date dateOfStart;
       
    @Column(name = "price", nullable = false)
   	private Long price;
    
    @OneToMany(mappedBy = "carOfImg", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private List<ImageEntity> images;
    
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn (name = "addressid", referencedColumnName = "id")
    private AddressEntity carAddress;
	
	
	@ManyToOne
	@JoinColumn(name = "staffid")
	private UserEntity staff_of_car;
	
	@OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<MaintenanceBillEntity> maintenanceBills;
	
	
	@ManyToOne
	@JoinColumn(name = "brandid")
    private CarBrandEntity brand;
	
	@ManyToOne
	@JoinColumn(name = "lineid")
    private CarLineEntity line;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
	    name = "car_contract",
	    joinColumns = @JoinColumn(name = "carid"),
	    inverseJoinColumns = @JoinColumn(name = "contractid")
	)
	private List<ContractEntity> contracts;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "car_voucher",
			 joinColumns = @JoinColumn(name = "carid"),
			 inverseJoinColumns = @JoinColumn(name = "voucherid")	
	)
	private List<VoucherEntity>  vouchers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getIndentify() {
		return indentify;
	}

	public void setIndentify(String indentify) {
		this.indentify = indentify;
	}

	public Date getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(Date dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public AddressEntity getCarAddress() {
		return carAddress;
	}

	public void setCarAddress(AddressEntity carAddress) {
		this.carAddress = carAddress;
	}

	public UserEntity getStaff_of_car() {
		return staff_of_car;
	}

	public void setStaff_of_car(UserEntity staff_of_car) {
		this.staff_of_car = staff_of_car;
	}

	public List<MaintenanceBillEntity> getMaintenanceBills() {
		return maintenanceBills;
	}

	public void setMaintenanceBills(List<MaintenanceBillEntity> maintenanceBills) {
		this.maintenanceBills = maintenanceBills;
	}

	public CarBrandEntity getBrand() {
		return brand;
	}

	public void setBrand(CarBrandEntity brand) {
		this.brand = brand;
	}

	public CarLineEntity getLine() {
		return line;
	}

	public void setLine(CarLineEntity line) {
		this.line = line;
	}

	public List<ContractEntity> getContracts() {
		return contracts;
	}

	public void setContracts(List<ContractEntity> contracts) {
		this.contracts = contracts;
	}

	public List<VoucherEntity> getVouchers() {
		return vouchers;
	}

	public void setVouchers(List<VoucherEntity> vouchers) {
		this.vouchers = vouchers;
	}

	public List<ImageEntity> getImages() {
		return images;
	}

	public void setImages(List<ImageEntity> images) {
		this.images = images;
	}

		
}
