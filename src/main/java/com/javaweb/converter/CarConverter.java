package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.entity.CarEntity;

public class CarConverter {
	private static ModelMapper modelMapper = new ModelMapper();

    public static CarEntity convertToEntity(InsertCarRequest insertCarRequest) {
        return modelMapper.map(insertCarRequest, CarEntity.class);
    }
}
