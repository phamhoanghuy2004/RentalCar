package com.javaweb.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;



@Entity
public class StaffEntity extends UserEntity {
	

    @Column(nullable = false)
	@Temporal(TemporalType.DATE)
    private Date dateOfStart;
	
    @Column(nullable = false)
	private int point;

	public Date getDateOfStart() {
		return dateOfStart;
	}
	
 	@OneToMany(mappedBy = "staff_quan_li", cascade = CascadeType.ALL)
 	private List<CarEntity> carEntities;
 	
 	@OneToMany(mappedBy = "staff_bao_tri", cascade = CascadeType.ALL)
 	private List<MaintenanceBillEntity> maintenanceBillEntity;
	 
	 
 	 public List<MaintenanceBillEntity> getMaintenanceBillEntity() {
		return maintenanceBillEntity;
	}

	public void setMaintenanceBillEntity(List<MaintenanceBillEntity> maintenanceBillEntity) {
		this.maintenanceBillEntity = maintenanceBillEntity;
	}

	@ManyToOne
     @JoinColumn(name = "address_staff_id", nullable = false)
     private AddressEntity address_staff_id;
	

	public List<CarEntity> getCarEntities() {
		return carEntities;
	}

	public void setCarEntities(List<CarEntity> carEntities) {
		this.carEntities = carEntities;
	}

	public void setDateOfStart(Date dateOfStart) {
		this.dateOfStart = dateOfStart;
	}

	

	public AddressEntity getAddress_staff_id() {
		return address_staff_id;
	}

	public void setAddress_staff_id(AddressEntity address_staff_id) {
		this.address_staff_id = address_staff_id;
	}

	
    public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	@ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role_staff;

	public RoleEntity getRole_staff() {
		return role_staff;
	}

	public void setRole_staff(RoleEntity role_staff) {
		this.role_staff = role_staff;
	}
	
}
