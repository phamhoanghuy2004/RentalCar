package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.CarEntity;
import com.javaweb.entity.CarLineEntity;

public interface CarLineRepository extends JpaRepository <CarLineEntity,Integer> {
	
}
