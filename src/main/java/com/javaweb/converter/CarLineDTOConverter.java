package com.javaweb.converter;

import org.modelmapper.ModelMapper;

import com.javaweb.beans.CarLineDTO;
import com.javaweb.entity.CarLineEntity;

public class CarLineDTOConverter {
	private static ModelMapper modelMapper = new ModelMapper();
	
    public static CarLineDTO convertToDTO(CarLineEntity carLineEnity) {
        return modelMapper.map(carLineEnity, CarLineDTO.class);
    }
}
