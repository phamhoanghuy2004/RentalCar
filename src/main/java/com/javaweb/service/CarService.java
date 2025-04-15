package com.javaweb.service;


import java.util.List;
import java.util.Map;

import com.javaweb.beans.CarDTO;
import org.springframework.http.ResponseEntity;
import com.javaweb.beans.request.InsertCarRequest;

public interface CarService {
	public List<CarDTO> getCarOfBrandActive(Long idBrand);
	public List<CarDTO> getNewCar();
	public List<CarDTO> getSaleCar();
	public List<CarDTO> getAllCar();
	public List<CarDTO> findCar(Map<String,Object> params);
	public ResponseEntity insertCar(InsertCarRequest carRequest);
}
