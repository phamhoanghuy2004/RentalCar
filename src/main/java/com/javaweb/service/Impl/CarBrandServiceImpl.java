package com.javaweb.service.Impl;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.beans.CarBrandDTO;
import com.javaweb.converter.CarBrandDTOConverter;
import com.javaweb.entity.CarBrandEntity;
import com.javaweb.repository.CarBrandRepository;
import com.javaweb.service.CarBrandService;

@Service
public class CarBrandServiceImpl implements CarBrandService  {
	
	@Autowired
	private CarBrandRepository carBrandRepository;
	
	@Autowired
	private CarBrandDTOConverter carBrandDTOConverter;


	@Override
	public List<CarBrandDTO> getAllCarBrandActive() {
		List<CarBrandEntity> listCarBrandEntity = carBrandRepository.findByStatus("Active");
		List<CarBrandDTO> listCarBrandDTO = new ArrayList<>();
		listCarBrandDTO = carBrandDTOConverter.convertCarBrandDTO(listCarBrandEntity);
		return listCarBrandDTO;
	}

}
