package com.javaweb.repository.custom;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.javaweb.entity.CarEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CarRepositoryCustomImpl implements CarRepositoryCustom   {
	
	 @PersistenceContext
	 private EntityManager entityManager;

	 @Override
	 @Transactional
	 public List<CarEntity> findCarsOnSale() {
		String sqlQuery = " SELECT DISTINCT c.* "
				+ " FROM car c "
				+ " INNER JOIN car_voucher cv ON c.id = cv.carid "
				+ " INNER JOIN voucher v ON cv.voucherid = v.id "
				+ " WHERE v.status = 'Active' ";
		Query query = entityManager.createNativeQuery(sqlQuery, CarEntity.class);
		List<CarEntity> rs = new ArrayList<>();
		rs = query.getResultList();
		return rs;
	}

}
