package com.javaweb.service;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;




public interface CarService {
	public ResponseEntity insertCar(InsertCarRequest carRequest);

}
