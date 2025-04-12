package com.javaweb.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class RoleEntity {
 	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 	

    @Column(name = "name" , nullable = false)
 	private String name;
 	

    @Column(name = "code" , nullable = false)
 	private String code;
 	
 	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<UserEntity> listUser ;


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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<UserEntity> getListUser() {
		return listUser;
	}

	public void setListUser(List<UserEntity> listUser) {
		this.listUser = listUser;
	}
 	
 		
}
