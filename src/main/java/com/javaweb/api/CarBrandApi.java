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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/carbrand")
@Tag(name = "Car Brand API" , description = "Các API liên quan đến hãng xe")
public class CarBrandApi {
	
	@Autowired
	private CarBrandService carBrandService;
	
	@Operation(
		summary = "Lấy tất cả các hãng xe đang hoạt động của hệ thống"
	)
	@GetMapping
	public Object GetAllCarBrand () {
		List<CarBrandDTO> listCarBrandDTO = carBrandService.getAllCarBrandActive();
		return listCarBrandDTO;
	}
	
	
	@Operation(
			summary = "Thêm một hãng xe vào hệ thống"
		)
	@PostMapping(value = "/insert")
	public ResultDTO<CarBrandEntity> insertBrand(@RequestBody CarBrandEntity brand){
		return carBrandService.insertBrand(brand);
	}
	
	
	@Operation(
			summary = "Chỉnh sửa một hãng xe"
		)
	@PutMapping(value = "/update")
	public ResultDTO<CarBrandEntity> updateBrand(@RequestBody CarBrandEntity brand){
		return carBrandService.updateBrand(brand);
	}
	
	
	@Operation(
			summary = "Xóa một hãng xe của hệ thống"
		)
	@DeleteMapping(value = "/delete/{id}")
	public ResultDTO<CarBrandEntity> deleteBrand(@PathVariable("id") Long id){
		return carBrandService.deleteBrand(id);
	}
	
}
