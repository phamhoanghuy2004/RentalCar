package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;



@Entity
@Table(name = "user")
public class UserEntity extends PersonEntity {
	

    @Column(name = "dateofstart" , nullable = false)
	@Temporal(TemporalType.DATE)
    private Date dateOfStart;
	
    @Column(name = "point", nullable = false)
	private Long point;

 	@OneToMany(mappedBy = "staff_of_car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<CarEntity> listCar;
 	
 	@OneToMany(mappedBy = "staff_of_bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<MaintenanceBillEntity> maintenanceBills;
	 
	@ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private RoleEntity role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "adressid")
	private AddressEntity userAddress;

	public Date getDateOfStart() {
		return dateOfStart;
	}

	public void setDateOfStart(Date dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public List<CarEntity> getListCar() {
		return listCar;
	}

	public void setListCar(List<CarEntity> listCar) {
		this.listCar = listCar;
	}

	public List<MaintenanceBillEntity> getMaintenanceBills() {
		return maintenanceBills;
	}

	public void setMaintenanceBills(List<MaintenanceBillEntity> maintenanceBills) {
		this.maintenanceBills = maintenanceBills;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public AddressEntity getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(AddressEntity userAddress) {
		this.userAddress = userAddress;
	}
	
	
}
