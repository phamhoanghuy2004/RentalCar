package com.javaweb.service;


import java.text.ParseException;
import java.util.List;
import java.util.Map;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.ContractDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.beans.CarResponse;
import org.springframework.http.ResponseEntity;
import com.javaweb.beans.request.BookingRequest;
import com.javaweb.beans.request.InsertCarRequest;
import com.nimbusds.jose.JOSEException;


public interface CarService {

	public List<CarDTO> getCarOfBrandActive(Long idBrand);
	public List<CarDTO> getNewCar();
	public List<CarDTO> getSaleCar();
	public List<CarDTO> getAllCar();
	public List<CarDTO> getCarByNameActive(String name);
	public List<CarResponse> getAllCarNoStatus();
	public List<CarDTO> findCar(Map<String,Object> params);
	public ResponseEntity insertCar(InsertCarRequest carRequest);
	public ResultDTO<ContractDTO> bookingCar (BookingRequest bookingRequest, String token) throws JOSEException, ParseException;
	public ResponseEntity updateCar(InsertCarRequest carRequest);
	public ResponseEntity deleteCar(Long id);


}
