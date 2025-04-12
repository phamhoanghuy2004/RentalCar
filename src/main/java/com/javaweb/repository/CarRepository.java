package com.javaweb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.entity.CarEntity;

public interface CarRepository extends JpaRepository <CarEntity,Integer> {
	List<CarEntity> findByBrand_IdAndStatus(int brandId, String status);
	@Modifying
	@Transactional
	@Query("UPDATE CarEntity c SET c.picture = :picture  WHERE c.id = :id")
	public int updatePicutre(@Param("id")  int id, @Param("picture") byte[] picture);
	
	List<CarEntity> findByDateOfStartBetween(Date startDate, Date endDate);
	
	@Query("SELECT c FROM CarEntity c LEFT JOIN c.contractEntities co GROUP BY c.id ORDER BY COUNT(co) DESC")
	List<CarEntity> findTop10ByOrderByHopDongsSizeDesc(Pageable pageable);
	
	
}
