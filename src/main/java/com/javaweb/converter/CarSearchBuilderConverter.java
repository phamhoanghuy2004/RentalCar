package com.javaweb.converter;

import java.util.Map;

import org.springframework.stereotype.Component;
import com.javaweb.builder.CarSearchBuilder;
import com.javaweb.util.MapUtil;

@Component
public class CarSearchBuilderConverter {
	public  CarSearchBuilder toCarSearchBuilder (Map<String,Object> params) {
		CarSearchBuilder carSearchBuilder = new CarSearchBuilder.Builder()
				.setLine(MapUtil.getObject(params, "line", String.class))
				.setBrand(MapUtil.getObject(params, "brand", String.class))
				.setLocation(MapUtil.getObject(params, "location", String.class))
				.setCarPriceFrom(MapUtil.getObject(params, "carPriceFrom", Long.class))
				.setCarPriceTo(MapUtil.getObject(params, "carPriceTo", Long.class))
				.build();
		
		return carSearchBuilder;
	}
}
