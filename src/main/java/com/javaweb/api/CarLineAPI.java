package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.CarBrandDTO;
import com.javaweb.beans.CarLineDTO;
import com.javaweb.service.CarBrandService;
import com.javaweb.service.CarLineService;

@RestController
@RequestMapping(value = "/api/carline")
public class CarLineAPI {
	@Autowired
	private CarLineService carLineService;
	
	
	@GetMapping
	public Object GetAllCarBrand () {
		List<CarLineDTO> carLineDTOs = carLineService.getAllCarLine();
		return carLineDTOs;
	}
	
}
