package com.javaweb.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.javaweb.entity.ContractEntity;


public interface ContractRepository extends JpaRepository<ContractEntity, Long>{
	@Query(" SELECT DISTINCT c FROM ContractEntity  c JOIN  c.cars car"
			+ " WHERE  car.id = :carId "
			+ " AND c.dateFrom <= :dateToRequest  "
			+ " AND c.dateTo >= :dateFromRequest ")
	List<ContractEntity> checkContract (  @Param("carId") Long  carId,
		    							@Param("dateFromRequest") Date dateFromRequest,
		    							@Param("dateToRequest") Date dateToRequest);
	
	@Query(" SELECT DISTINCT c FROM ContractEntity c  WHERE c.customer.id = :cusID ORDER BY c.id DESC ")
	List<ContractEntity> getContractByCusId (@Param("cusID") Long cusID);
	
	ContractEntity findByIdAndStatus(Long id, String status);
}
