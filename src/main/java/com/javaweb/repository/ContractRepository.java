package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.ContractEntity;

public interface ContractRepository extends JpaRepository<ContractEntity, Long>{
	
}
