package com.javaweb.service.Impl;


import java.util.ArrayList;
import java.util.Date;
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
import com.javaweb.beans.CarResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.builder.CarSearchBuilder;
import com.javaweb.entity.AddressEntity;
import com.javaweb.entity.CarBrandEntity;
import com.javaweb.entity.CarEntity;
import com.javaweb.entity.CarLineEntity;
import com.javaweb.entity.ImageEntity;
import com.javaweb.converter.AddressConverter;
import com.javaweb.converter.CarConverter;
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
	@Transactional
	public List<CarResponse> getAllCarNoStatus() {
		List<CarEntity> listCarEntity = carRepository.findAll();
	    List<CarResponse> carResponses = new ArrayList<>();

	    for (CarEntity car : listCarEntity) {
	        CarResponse response = carDTOConverter.mapCarEntityToResponse(car);
	        carResponses.add(response);
	    }

	    return carResponses;
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

	@Override
	@Transactional
	public ResponseEntity updateCar(InsertCarRequest request) {
		try {
			CarEntity car = carRepository.findById(request.getId())
		            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy xe với ID: " + request.getId()));

		    car.setName(request.getName());
		    car.setDescription(request.getDescription());
		    car.setStatus(request.getStatus());
		    car.setIndentify(request.getIndentify());
		    car.setPrice(request.getPrice());

		    CarBrandEntity brand = carBrandRepository.findById(request.getBrandId())
		            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy hãng xe"));
		    car.setBrand(brand);

		    CarLineEntity line = carLineRepository.findById(request.getLineId())
		            .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy dòng xe"));
		    car.setLine(line);

		    AddressEntity address = car.getCarAddress();
		    if (address == null) {
		        address = new AddressEntity();
		        address.setCar(car);
		    }
		    address.setProvince(request.getProvince());
		    address.setDistrict(request.getDistrict());
		    address.setWard(request.getWard());
		    address.setStreet(request.getStreet());
		    car.setCarAddress(address);

		    carRepository.save(car);

		    if (request.getImageUrls() != null) {
		        imageRepository.deleteByCarOfImg(car);

		        List<ImageEntity> images = request.getImageUrls().stream()
		                .map(url -> {
		                    ImageEntity image = new ImageEntity();
		                    image.setData(url);
		                    image.setCarOfImg(car);
		                    return image;
		                })
		                .collect(Collectors.toList());

		        imageRepository.saveAll(images);
		    }
	        return ResponseEntity.ok("Cập nhật thành công");
		} catch (Exception e) {
			e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		               .body("Lỗi khi cập nhật xe: " + e.getMessage());
		}	
	}

	@Override
	public ResponseEntity deleteCar(Long id) {
		try {
			CarEntity car = carRepository.findById(id).get();
			car.setStatus("Deleted");
			carRepository.save(car);
			return ResponseEntity.ok("Xóa thành công");
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		               .body("Lỗi khi xóa xe: " + e.getMessage());
		}
	}

}
