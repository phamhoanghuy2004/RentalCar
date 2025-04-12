package com.javaweb.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.javaweb.entity.CarEntity;
import com.javaweb.repository.custom.CarRepositoryCustom;

public interface CarRepository  extends JpaRepository<CarEntity, Long>, CarRepositoryCustom  {
	List<CarEntity> findByBrand_IdAndStatus(Long brandId, String status);
	List<CarEntity> findTop7ByStatus(String status);
	List<CarEntity> findByStatus(String status);
}
