package com.javaweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.javaweb.entity.CustomerEntity;


public interface CustomerRepository extends JpaRepository<CustomerEntity,Long>{
	Optional<CustomerEntity> findByEmail(String email);
	Optional<CustomerEntity> findByEmailAndPassword(String email, String password);
}
