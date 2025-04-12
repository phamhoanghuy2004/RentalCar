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
	
//	@Override
//	public Object lessThanSevenDay() {
//		LocalDate localDate = LocalDate.now().minusDays(7);
//		Date startDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		localDate = LocalDate.now();
//		Date endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//		List<CarEntity> cars = carRepository.findByDateOfStartBetween(startDate,endDate);
//		List<CarDTO> carDTOs = convertToDTOList(cars);	
//		return carDTOs;S
//	}
	
	public CarDTO convertToDTO(CarEntity carEntity) {
        return modelMapper.map(carEntity, CarDTO.class);
    }

    public List<CarDTO> convertToDTOList(List<CarEntity> carEntities) {
        return carEntities.stream().map(car -> modelMapper.map(car, CarDTO.class))
                          .collect(Collectors.toList());
    }

//	@Override
//	public Object topTen() {
//		PageRequest topTen = PageRequest.of(0, 10);
//		List<CarEntity> cars = carRepository.findTop10ByOrderByHopDongsSizeDesc(topTen);
//		List<CarDTO> carDTOs = convertToDTOList(cars);	
//		return carDTOs;
//	}
	
	
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

	

//	@Override
//	public int updateLogo(int carId, MultipartFile file) throws IOException {
//	    if (file.isEmpty()) {
//	        return 0;
//	    }
//	    // Cập nhật mảng byte vào database (THAY VÌ LƯU TÊN FILE)
//	    return carRepository.updatePicutre(carId, file.getBytes());
//	    
//	}

}
