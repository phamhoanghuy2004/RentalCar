package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.javaweb.beans.CarBrandDTO;
import com.javaweb.service.CarBrandService;

@RestController
@RequestMapping(value = "/api/carbrand")
public class CarBrandApi {
	
	@Autowired
	private CarBrandService carBrandService;
	
	
	@GetMapping
	public Object GetAllCarBrand () {
		List<CarBrandDTO> listCarBrandDTO = carBrandService.getAllCarBrandActive();
		return listCarBrandDTO;
	}
	
}
