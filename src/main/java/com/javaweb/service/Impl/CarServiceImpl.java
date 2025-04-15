package com.javaweb.service.Impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.javaweb.repository.CarRepository;
import com.javaweb.beans.CarDTO;
import org.springframework.http.ResponseEntity;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.builder.CarSearchBuilder;
import com.javaweb.entity.CarEntity;
import com.javaweb.converter.CarDTOConverter;
import com.javaweb.converter.CarSearchBuilderConverter;
import com.javaweb.service.CarService;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private CarDTOConverter carDTOConverter;
	
	@Autowired
	private CarSearchBuilderConverter carSearchBuilderConverter;
	
	
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
	public List<CarDTO> getAllCar() {
		List<CarEntity> listCarEntity = carRepository.findByStatus("Active");
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

	@Override
	public List<CarDTO> findCar(Map<String, Object> params) {
		CarSearchBuilder carSearchBuilder = carSearchBuilderConverter.toCarSearchBuilder(params);
		List<CarEntity> listCarEntity = carRepository.findCar(carSearchBuilder);
		List<CarDTO> listCarDTO = carDTOConverter.convertCarDTO(listCarEntity);
		return listCarDTO; 
	}



}
