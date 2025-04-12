package com.javaweb.converter;

import org.modelmapper.ModelMapper;

import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.entity.AddressEntity;

public class AddressConverter {
	private static ModelMapper modelMapper = new ModelMapper();

    public static AddressEntity convertToEntity(InsertCarRequest insertCarRequest) {
        return modelMapper.map(insertCarRequest, AddressEntity.class);
    }
}
