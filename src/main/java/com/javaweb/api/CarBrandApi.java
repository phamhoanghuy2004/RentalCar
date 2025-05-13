package com.javaweb.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.javaweb.beans.CarBrandDTO;
import com.javaweb.beans.ResultDTO;
import com.javaweb.entity.CarBrandEntity;
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
	
	@PostMapping(value = "/insert")
	public ResultDTO<CarBrandEntity> insertBrand(@RequestBody CarBrandEntity brand){
		return carBrandService.insertBrand(brand);
	}
	
	@PutMapping(value = "/update")
	public ResultDTO<CarBrandEntity> updateBrand(@RequestBody CarBrandEntity brand){
		return carBrandService.updateBrand(brand);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public ResultDTO<CarBrandEntity> deleteBrand(@PathVariable("id") Long id){
		return carBrandService.deleteBrand(id);
	}
	
}
