package com.javaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.entity.CarBrandEntity;

public interface CarBrandRepository extends JpaRepository<CarBrandEntity,Integer> {
	public List<CarBrandEntity>  findAll();
	public List<CarBrandEntity>  findByStatus(int status);	
	
	@Modifying
	@Transactional
	@Query("UPDATE CarBrandEntity c SET c.logo = :logo  WHERE c.id = :id")
	public int updatePicutre(@Param("id")  int id, @Param("logo") byte[] picture);
	
}
