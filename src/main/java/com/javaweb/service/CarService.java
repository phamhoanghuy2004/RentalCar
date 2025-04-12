package com.javaweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;




public interface CarService {
	public List<CarDTO> getCarOfBrandActive(int idBrand);
	public int updateLogo(int id, MultipartFile file)  throws IOException;
	public ResponseEntity insertCar(InsertCarRequest carRequest);
	Object lessThanSevenDay();
	Object topTen();
}
