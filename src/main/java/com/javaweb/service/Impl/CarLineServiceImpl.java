package com.javaweb.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.beans.CarDTO;
import com.javaweb.beans.CarLineDTO;
import com.javaweb.converter.CarLineDTOConverter;
import com.javaweb.entity.CarEntity;
import com.javaweb.entity.CarLineEntity;
import com.javaweb.repository.CarLineRepository;
import com.javaweb.service.CarLineService;

@Service
public class CarLineServiceImpl implements CarLineService {
	
	@Autowired
	private CarLineRepository carLineRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	
    public List<CarLineDTO> convertToDTOList(List<CarLineEntity> carLineEntities) {
        return carLineEntities.stream().map(car -> modelMapper.map(car, CarLineDTO.class))
                          .collect(Collectors.toList());
    }

	@Override
	public List<CarLineDTO> getAllCarLine() {
		List<CarLineEntity> carLineEntities = carLineRepository.findAll();
		List<CarLineDTO> carLineDTOs = new ArrayList<>();
		for(CarLineEntity item : carLineEntities) {
			CarLineDTO carLineDTO = CarLineDTOConverter.convertToDTO(item);
			carLineDTOs.add(carLineDTO);
		}
		return carLineDTOs;
	}
}
