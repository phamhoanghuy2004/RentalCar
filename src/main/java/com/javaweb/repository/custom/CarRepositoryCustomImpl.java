package com.javaweb.repository.custom;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.CarSearchBuilder;
import com.javaweb.entity.CarEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

	@Override
	@Transactional
	public List<CarEntity> findCar(CarSearchBuilder carSearchBuilder) {
		StringBuilder  sqlQuery =  new StringBuilder (" SELECT c FROM CarEntity c ");
		addJoin(sqlQuery,carSearchBuilder);
		StringBuilder where =  new StringBuilder (" WHERE 1 = 1 ");
		Map<String,Object> params = new HashMap<>();
		queryVip(where,carSearchBuilder,params);
		sqlQuery.append(where);
		
		// add tham so 
		TypedQuery<CarEntity> query = entityManager.createQuery(sqlQuery.toString(), CarEntity.class);
		params.forEach(query::setParameter);
		return query.getResultList();
	}
	
	private void addJoin (StringBuilder sqlQuery, CarSearchBuilder carSearchBuilder ) {
		if (carSearchBuilder.getLine() != null && !carSearchBuilder.getLine().equals("")) {
			sqlQuery.append(" JOIN c.line l ");
		}
		if (carSearchBuilder.getBrand() != null && !carSearchBuilder.getBrand().equals("") ) {
			sqlQuery.append(" JOIN c.brand br ");
		}
		if (carSearchBuilder.getLocation() != null && !carSearchBuilder.getLocation().equals("")) {
			sqlQuery.append(" JOIN c.carAddress a");
		}
	}
	
	private void queryVip (StringBuilder where, CarSearchBuilder carSearchBuilder, Map<String,Object> params ) {
			
			String carLine = carSearchBuilder.getLine();
			if (carLine != null && !carLine.equals("")) {
				where.append(" AND l.name LIKE :line ");
				params.put("line", "%" + carSearchBuilder.getLine() + "%");
			}
			
			String carBrand = carSearchBuilder.getBrand();
			if (carBrand != null && !carBrand.equals("")) {
				where.append(" AND br.name LIKE :brand ");
				params.put("brand", "%" + carSearchBuilder.getBrand() + "%");
			}
			
			String location = carSearchBuilder.getLocation();
			if (location != null && !location.equals("")) {
				where.append(" AND a.province LIKE :location ");
				 params.put("location", "%" + carSearchBuilder.getLocation() + "%");
			}
			
			Long carPriceFrom = carSearchBuilder.getCarPriceFrom();
			Long carPriceTo = carSearchBuilder.getCarPriceTo();
			
			if (carPriceFrom != null || carPriceTo != null) {
				if (carPriceFrom != null) {
					where.append(" AND c.price >= :priceFrom " );
					params.put("priceFrom", carPriceFrom);
				}
				if (carPriceTo != null) {
					where.append(" AND c.price <= :priceTo " );
					params.put("priceTo", carPriceTo);
				}
			}
	}

}
