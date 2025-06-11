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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/carline")
@Tag(name = "Carline API", description = "Các API liên quan đến dòng xe")
public class CarLineAPI {
	@Autowired
	private CarLineService carLineService;
	
	@Operation(
			summary = "Lấy các dòng xe"
		)
	@GetMapping
	public Object GetAllCarBrand () {
		List<CarLineDTO> carLineDTOs = carLineService.getAllCarLine();
		return carLineDTOs;
	}
	
}
