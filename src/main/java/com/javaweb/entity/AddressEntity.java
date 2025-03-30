package com.javaweb.entity;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "province", nullable = false)
	private String province;  //Tỉnh/Thành phố
	
	@Column(name = "district", nullable = false)
	private String district;  //Quận/Huyện
	
	
	@Column(name = "ward", nullable = false)
	private String ward;   //Phường/Xã
	
	@Column(name = "street", nullable = false)
	private String street;  // địa chỉ chi tiết 
	
	@OneToOne(mappedBy = "userAddress", fetch = FetchType.EAGER)
    private UserEntity user;
	
	@OneToOne(mappedBy = "carAddress", fetch = FetchType.EAGER)
    private CarEntity car;
	
	@OneToOne(mappedBy = "customerAddress", fetch = FetchType.EAGER)
    private CustomerEntity customer;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public PersonEntity getUser() {
		return user;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}

	public CustomerEntity getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}


}
