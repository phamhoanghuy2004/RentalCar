package com.javaweb.repository.custom;

import java.util.List;
import com.javaweb.entity.CarEntity;

public interface CarRepositoryCustom {
	List<CarEntity> findCarsOnSale ();
}
