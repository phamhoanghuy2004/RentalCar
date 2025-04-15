package com.javaweb.repository.custom;

import java.util.List;

import com.javaweb.builder.CarSearchBuilder;
import com.javaweb.entity.CarEntity;

public interface CarRepositoryCustom {
	List<CarEntity> findCarsOnSale ();
	List<CarEntity> findCar (CarSearchBuilder carSearchBuilder);
}
