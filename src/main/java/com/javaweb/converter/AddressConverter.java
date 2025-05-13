package com.javaweb.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.beans.request.updateProfileRequest;
import com.javaweb.entity.AddressEntity;


@Component
public class AddressConverter {
	private static ModelMapper modelMapper = new ModelMapper();

    public static AddressEntity convertToEntity(InsertCarRequest insertCarRequest) {
        return modelMapper.map(insertCarRequest, AddressEntity.class);
    }
    
    public AddressEntity convertToEntityFromCusRequest (updateProfileRequest request) {
        return modelMapper.map(request, AddressEntity.class);
    }
}
