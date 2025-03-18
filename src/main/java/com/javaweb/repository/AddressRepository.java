package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long>{

}
