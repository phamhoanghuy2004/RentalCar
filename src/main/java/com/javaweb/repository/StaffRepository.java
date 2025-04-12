package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Integer>{

}
