package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaweb.beans.CarDTO;
import com.javaweb.beans.request.InsertCarRequest;
import com.javaweb.service.CarService;


@RestController
@RequestMapping(value = "/api/car")
public class CarApi {
	@Autowired
	private CarService carService;
	

    @PostMapping("insertCar")
    public ResponseEntity<?> InsertCar(@RequestBody InsertCarRequest carRequest){
    	ResponseEntity response = carService.insertCar(carRequest);
    	return response;
    }

	
}
