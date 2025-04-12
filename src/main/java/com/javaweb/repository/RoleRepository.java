package com.javaweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long>{

}
