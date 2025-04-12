package com.javaweb.service.Impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.repository.CarRepository;
import com.javaweb.beans.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.javaweb.repository.CarRepository;
import com.javaweb.beans.CarDTO;

import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.converter.AddressConverter;
import com.javaweb.converter.CarConverter;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CarBrandEntity;
import com.javaweb.entity.CarEntity;
import com.javaweb.entity.CarLineEntity;
import com.javaweb.converter.CarDTOConverter;
import com.javaweb.entity.CarEntity;
import com.javaweb.entity.ImageEntity;
import com.javaweb.service.CarService;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private CarDTOConverter carDTOConverter;
	
	
	CarDTO convertToDTO(CarEntity carEntity) {
        return modelMapper.map(carEntity, CarDTO.class);
    }

    public List<CarDTO> convertToDTOList(List<CarEntity> carEntities) {
        return carEntities.stream().map(car -> modelMapper.map(car, CarDTO.class))
                          .collect(Collectors.toList());
    }
	
	@Override
	public List<CarDTO> getCarOfBrandActive(Long idBrand) {
		
		List<CarEntity> listCarEntity = carRepository.findByBrand_IdAndStatus(idBrand, "Active");
		List<CarDTO> listCarDTO = new ArrayList<>();
		listCarDTO = carDTOConverter.convertCarDTO(listCarEntity);
		return listCarDTO;
		
	}

	@Override
	public List<CarDTO> getNewCar() {
		List<CarEntity> listCarEntity = carRepository.findTop7ByStatus("Active");
		List<CarDTO> listCarDTO = new ArrayList<>();
		listCarDTO = carDTOConverter.convertCarDTO(listCarEntity);
		return listCarDTO;
	}

	@Override
	public List<CarDTO> getSaleCar() {
		List<CarEntity> listCarEntity = carRepository.findCarsOnSale();
		List<CarDTO> listCarDTO = new ArrayList<>();
		listCarDTO = carDTOConverter.convertCarDTO(listCarEntity);
		return listCarDTO;
	}

	


    @Override
	public ResponseEntity insertCar(InsertCarRequest insertCarRequest) {
//		AddressEntity newAddress = AddressConverter.convertToEntity(insertCarRequest);
//		newAddress = addressRepository.save(newAddress);
//		int brandId = insertCarRequest.getBrandId();
//		int lineId = insertCarRequest.getLineId();
//		CarBrandEntity brand = carBrandRepository.findById(brandId).get();
//		CarLineEntity line = carLineRepository.findById(lineId).get();
//		CarEntity newCar = CarConverter.convertToEntity(insertCarRequest);
////		newCar.setAddress_car_id(newAddress);
////		newCar.setBrand(brand);
////		newCar.setC
////		carRepository.save(newCar);
//		return ResponseEntity.ok("Them thanh cong");
    	return null;
	}



}
