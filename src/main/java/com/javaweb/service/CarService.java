package com.javaweb.service;


import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.CarResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.entity.CarEntity;


public interface CarService {

	public List<CarDTO> getCarOfBrandActive(Long idBrand);
	public List<CarDTO> getNewCar();
	public List<CarDTO> getSaleCar();
	public List<CarDTO> getAllCar();
	public List<CarResponse> getAllCarNoStatus();
	public List<CarDTO> findCar(Map<String,Object> params);
	public ResponseEntity insertCar(InsertCarRequest carRequest);
	public ResponseEntity updateCar(InsertCarRequest carRequest);
	public ResponseEntity deleteCar(Long id);

}
