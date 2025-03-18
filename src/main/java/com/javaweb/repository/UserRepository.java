package com.javaweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.entity.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
	Optional<CustomerEntity> findByEmail(String email);
	Optional<CustomerEntity> findByEmailAndPassword(String email, String password);
}
