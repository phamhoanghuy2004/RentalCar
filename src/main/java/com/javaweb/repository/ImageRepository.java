package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.CarEntity;
import com.javaweb.entity.ImageEntity;

public interface ImageRepository extends JpaRepository<ImageEntity,Long>{
	void deleteByCarOfImg(CarEntity car);
}
