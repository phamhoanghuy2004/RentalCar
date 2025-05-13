package com.javaweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.PersonEntity;

public interface CustomerRepository extends JpaRepository<PersonEntity,Long>{
	Optional<CustomerEntity> findByEmail(String email);
}
