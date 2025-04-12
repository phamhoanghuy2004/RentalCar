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
import com.javaweb.repository.custom.CarRepositoryCustom;

public interface CarRepository  extends JpaRepository<CarEntity, Long>, CarRepositoryCustom  {
	List<CarEntity> findByBrand_IdAndStatus(Long brandId, String status);
	List<CarEntity> findTop7ByStatus(String status);
	
	
	@Query("SELECT c FROM CarEntity c LEFT JOIN c.contractEntities co GROUP BY c.id ORDER BY COUNT(co) DESC")
	List<CarEntity> findTop10ByOrderByHopDongsSizeDesc(Pageable pageable);
	

	

}
