package com.javaweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.CarLineEntity;

public interface CarLineRepository extends JpaRepository<CarLineEntity,Long>{

}
