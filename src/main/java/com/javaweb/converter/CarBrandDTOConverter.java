package com.javaweb.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.beans.CarBrandDTO;
import com.javaweb.entity.CarBrandEntity;


@Component
public class CarBrandDTOConverter {
	@Autowired
	private ModelMapper modelMapper;
	
	public List<CarBrandDTO> convertCarBrandDTO (List<CarBrandEntity> listCarBrandEntity){
		List<CarBrandDTO> listCarBrandDTO = new ArrayList<>();
		for (CarBrandEntity brand : listCarBrandEntity) {
			CarBrandDTO carBrandDTO = new CarBrandDTO();
			carBrandDTO = modelMapper.map(brand,CarBrandDTO.class); // map nhung thang co ban
			listCarBrandDTO.add(carBrandDTO);
		}
		return listCarBrandDTO; 
	}
}
