package com.javaweb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javaweb.entity.CarBrandEntity;

public interface CarBrandRepository extends JpaRepository<CarBrandEntity,Long> {
	public List<CarBrandEntity>  findAll();
	public List<CarBrandEntity>  findByStatus(String status);	
	
}
