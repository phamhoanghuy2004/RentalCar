package com.javaweb.service.Impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.javaweb.repository.AddressRepository;
import com.javaweb.repository.CarBrandRepository;
import com.javaweb.repository.CarLineRepository;
import com.javaweb.repository.CarRepository;
import com.javaweb.repository.ImageRepository;
import com.javaweb.beans.CarDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.builder.CarSearchBuilder;
import com.javaweb.entity.CarEntity;
import com.javaweb.converter.CarDTOConverter;
import com.javaweb.converter.CarSearchBuilderConverter;
import com.javaweb.service.CarService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CarServiceImpl implements CarService{
	
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CarBrandRepository carBrandRepository;
	
	@Autowired
	private CarLineRepository carLineRepository;
	
	@Autowired
	private ImageRepository imageRepository;
	
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
	@Transactional
	public List<CarDTO> getAllCar() {
		List<CarEntity> listCarEntity = carRepository.findByStatus("Active");
		List<CarDTO> listCarDTO = new ArrayList<>();
		listCarDTO = carDTOConverter.convertCarDTO(listCarEntity);
		return listCarDTO;
	}

	
  @Override
	public ResponseEntity insertCar(InsertCarRequest insertCarRequest) {
	    try {
	        AddressEntity newAddress = AddressConverter.convertToEntity(insertCarRequest);	      
	        CarBrandEntity brand = carBrandRepository.findById(insertCarRequest.getBrandId())
	                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy hãng xe"));
	        CarLineEntity line = carLineRepository.findById(insertCarRequest.getLineId())
	                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy loại xe"));
	        
	        CarEntity newCar = CarConverter.convertToEntity(insertCarRequest);
	        newCar.setDateOfStart(new Date());
	        newCar.setBrand(brand);
	        newCar.setLine(line);
	        
	        newCar.setCarAddress(newAddress); 
	        newAddress.setCar(newCar);        
	        
	        carRepository.save(newCar);
	        
	        if (insertCarRequest.getImageUrls() != null && !insertCarRequest.getImageUrls().isEmpty()) {
	            List<ImageEntity> images = insertCarRequest.getImageUrls().stream()
	                .map(url -> {
	                    ImageEntity image = new ImageEntity();
	                    image.setData(url);
	                    image.setCarOfImg(newCar);
	                    return image;
	                })
	                .collect(Collectors.toList());
	            imageRepository.saveAll(images);
	        }
	        
	        return ResponseEntity.ok("Thêm xe thành công");
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	               .body("Lỗi khi thêm xe: " + e.getMessage());
	    }
	}

	@Override
	public List<CarDTO> findCar(Map<String, Object> params) {
		CarSearchBuilder carSearchBuilder = carSearchBuilderConverter.toCarSearchBuilder(params);
		List<CarEntity> listCarEntity = carRepository.findCar(carSearchBuilder);
		List<CarDTO> listCarDTO = carDTOConverter.convertCarDTO(listCarEntity);
		return listCarDTO; 
	}

}
